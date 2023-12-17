import React, { useState, useEffect } from "react";
import { Link, useParams, useNavigate, useLocation } from "react-router-dom"; // Agregamos useParams para obtener el parámetro de la URL
import Error from "./Error";
import DatePicker from "react-datepicker";
import axios from "axios"

function EditarVotacion({ votacionOriginal }) {
    // Obtiene los parámetros de la URL y la ubicación actual
    const location = useLocation();
    const pathSegments = location.pathname.split("/");
    const iduser = pathSegments[pathSegments.length - 1];
    const idVotacion = pathSegments[pathSegments.length - 2];
    // Estados para los datos de la votación
    const [nombreVotacion, setNombreVotacion] = useState("");
    const [descripcionVotacion, setDescripcionVotacion] = useState("");
    const [fechaInicio, setFechaInicio] = useState("");
    const [fechaFinalizacion, setFechaFinalizacion] = useState("");
    const [mostrarErrores, setMostrarErrores] = useState(false);
    const navigate = useNavigate();
    const { votacionId } = useParams();
    const today = new Date();
    // Estado para la lista de candidatos
    const [candidatos, setCandidatos] = useState([
        { nombre: "", apellido: "", nombreLista: "", numLista: "", },
        { nombre: "", apellido: "", nombreLista: "", numLista: "", }
    ]);
    // Función que se ejecuta al cargar el componente
    useEffect(() => {
        fetchData();
    }, []);

    // Función para obtener los datos de la votación y candidatos
    const fetchData = () => {
        axios.get("http://localhost:8080/api/votacion/find", {params: {idVotacion: idVotacion}}).then(response => {
            const datos = response.data.data;
            setNombreVotacion(datos.nombre);
            setDescripcionVotacion(datos.descripcion);
            setFechaInicio(new Date(datos.fechaInicio));
            setFechaFinalizacion(new Date(datos.fechaFinalizacion));
            fetchCandidatos();
        })
    }
    // Función para obtener la lista de candidatos
    const fetchCandidatos = () => {
        axios.get("http://localhost:8080/api/candidato/findAll", {params: {code: idVotacion}}).then(response => {
            const datos = response.data.data;
            setCandidatos(datos);
        })
    }
    // Función para eliminar un candidato
    const eliminarCandidato = (index) => {
        console.log()
        if (index.id != null) {
            axios.get("http://localhost:8080/api/candidato/eliminarCandidato", {params: {id: id}}).then(response => {
                fetchData();
            })
        }
    }
    
    // Función para agregar un nuevo candidato en la lista de opciones 
    const agregarCandidato = () => {
        setCandidatos([...candidatos, {
            nombre: "",
            apellido: "",
            nombreLista: "",
            numLista: "",
        }]);
    };

    // Función para quitar un candidato de la lista de opciones 
    const quitarCandidato = (index) => {
        const nuevosCandidatos = candidatos.filter((_, i) => i !== index);
        setCandidatos(nuevosCandidatos);
    };


    // Función para guardar los cambios en la votación editada
    const guardarEdicion = () => {
        // Devuelve un valor que verififica que los campos de los candidatos están vaciós
        const candidatosSonValidos = () => {
            return candidatos.every(candidato =>
                candidato.nombre !== "" && candidato.apellido !== "" && candidato.nombreLista !== "" && candidato.numLista !== ""
                && !/^\s*$/.test(candidato.nombre) && !/^\s*$/.test(candidato.apellido) && !/^\s*$/.test(candidato.nombreLista) && /^[0-9]+$/.test(candidato.numLista)
            );
        };
        // Verifica si todos los campos están llenos, caso contrario se mostrará mensejas de error
        if (nombreVotacion === "" || descripcionVotacion === "" || fechaInicio === "" || fechaFinalizacion === "" || candidatos.length < 2 || !candidatosSonValidos()
            || /^\s*$/.test(nombreVotacion) || /^\s*$/.test(descripcionVotacion)) {
            setMostrarErrores(true);
            return;
        } else {
            setMostrarErrores(false);

            // Una vez que no haya errores, se crea el objeto votación con los campos establecidos
            const votacionEditada = {
                id: votacionId,
                nombre: nombreVotacion,
                descripcion: descripcionVotacion,
                fechaInicio: fechaInicio,
                fechaFinalizacion: fechaFinalizacion,
                candidatos: candidatos,
            };
            console.log("votacionEditada", votacionEditada);
            // Enviar una solicitud POST para editar la votación en el servidor
            axios.post("http://localhost:8080/api/votacion/edit", votacionEditada).then(response => {
                console.log("Votación Editada:", votacionEditada);
            }).catch(error => { console.log("error:", error.msg) })

            // Navega de regreso a la pantalla de inicio del usuario
            navigate(`/Pantalla_Inicio/${iduser}`);
        }
    };

    return (
        <div>
            <header className="bg-indigo-400 p-5 flex justify-between items-center">
                <div className="text-white font-bold text-2xl">VoxChoice: Tu Voz, Tu Elección</div>
            </header>
             {/* Botón de Volver hacia la pantalla de inicio del usuario */}
            <div className="flex justify-end items-center mr-7 mt-5 mb-5">
                <Link to={`/Pantalla_Inicio/${iduser}`} className="bg-indigo-600 hover:bg-indigo-800 text-white px-10 py-2 ml-4 rounded">
                    Volver
                </Link>
            </div>
            {/* Al inicio de establece donde aparecerá el mensaje de error en caso de que los campos no esten llenos, seguido de los campos especificados de la votación que el usuario debe ingresar, entre esos
            están el nombre, descripción, la fecha en la que la votación estará habilitada (inicio) así como la fecha en la que se va a deshabilitar la votación (finalización), todas estas fechas están con sus respectivas 
            restricciones como deshabilitar fechas anteriores a la actual y demás.  */}
            <div className="ml-7 mr-7">
                {mostrarErrores && <Error>Campos Inválidos</Error>}
                <label className="block font-bold text-lg mb-2">Nombre de la Votación</label>

                <input
                    id="nombre_votacion"
                    className="w-full p-2 border rounded shadow-md rounded-lg"
                    type="text"
                    placeholder="Nombre de la Votación"
                    value={nombreVotacion}
                    onChange={(e) => setNombreVotacion(e.target.value)}
                />
                <label className="block font-bold text-lg mt-5 mb-5">ID: {votacionId}</label>
                <label className="block font-bold text-lg mt-3">Descripción de la Votación</label>
                <input
                    id="descripcion_votacion"
                    className="w-full p-2 border rounded shadow-md rounded-lg"
                    type="text"
                    placeholder="Descripción de la Votación"
                    value={descripcionVotacion}
                    onChange={(e) => setDescripcionVotacion(e.target.value)}
                />
                <div className="flex space-x-8 ">
                    <div>
                        <label className="block font-bold text-lg mt-3">Fecha de Inicio</label>
                        <DatePicker
                            selected={fechaInicio}
                            onChange={date => setFechaInicio(date)}
                            className="w-full p-3 border rounded shadow-md rounded-lg"
                            placeholderText="Inicio de Votación"
                            calendarClassName="custom-calendar"
                            minDate={today}
                            maxDate={fechaFinalizacion}
                        />
                    </div>
                    <div>
                        <label className="block font-bold text-lg mt-3">Fecha de Finalización</label>
                        <DatePicker
                            selected={fechaFinalizacion}
                            onChange={date => {
                                // Si la fecha seleccionada es anterior a la fecha de inicio, actualiza la fecha de inicio
                                if (date < fechaInicio) {
                                    setFechaInicio(date);
                                }
                                setFechaFinalizacion(date);
                            }}
                            className="w-full p-3 border rounded shadow-md rounded-lg"
                            placeholderText="Fin de Votación"
                            calendarClassName="custom-calendar"
                            minDate={fechaInicio || today} // No permite seleccionar una fecha anterior a la de inicio

                        />
                    </div>
                </div>
            </div>
            {/* En este apartado se dispone sobre la información de los candidatos, tenemos el botón de agregar para aumentar la cantidad de 
            elementos a elegir con sus respectivos campos como nombre y apellido del candidato, asi como el nombre y el número de lista a la que corresponden,
            de igual manera tenemos que a lado de cada opción (candidato) está el botón de eliminar para que se pueda quitar por cada elemento, cabe recalcar que 
            no se podrá eliminar si es que solo hay dos opciones, puesto que debe haber mínimo dos elementos para votar*/}
            <div className="ml-7 mr-7">
                <label className="block font-bold text-lg">Candidatos a Votar</label>
                <div className="flex items-center justify-between mt-5 mb-5">
                    <button
                        className="bg-blue-500 hover:bg-blue-600 text-white px-7 py-2 rounded"
                        onClick={agregarCandidato}
                    >
                        Agregar Candidato
                    </button>
                </div>

            </div>

            <div className="ml-7 mt-2 mr-7">
                {candidatos.map((candidato, index) => (
                    <div key={index} className="flex">
                        <input
                            className="w-full p-2 mb-2 border rounded shadow-md rounded-lg"
                            type="text"
                            placeholder={`Nombre del Candidato ${index + 1}`}
                            value={candidato.nombre}
                            onChange={(e) => {
                                const nuevosCandidatos = [...candidatos];
                                nuevosCandidatos[index].nombre = e.target.value;
                                setCandidatos(nuevosCandidatos);
                            }}
                        />
                        <input
                            className="w-full p-2 mb-2 border rounded shadow-md rounded-lg"
                            type="text"
                            placeholder={`Apellido del Candidato ${index + 1}`}
                            value={candidato.apellido}
                            onChange={(e) => {
                                const nuevosCandidatos = [...candidatos];
                                nuevosCandidatos[index].apellido = e.target.value;
                                setCandidatos(nuevosCandidatos);
                            }}
                        />
                        <input
                            className="w-full p-2 mb-2 border rounded shadow-md rounded-lg"
                            type="text"
                            placeholder={`Número de la Lista para el Candidato ${index + 1}`}
                            value={candidato.numLista}
                            onChange={(e) => {
                                const nuevosCandidatos = [...candidatos];
                                nuevosCandidatos[index].numLista = e.target.value;
                                setCandidatos(nuevosCandidatos);
                            }}
                        />
                        <input
                            className="w-full p-2 mb-2 border rounded shadow-md rounded-lg"
                            type="text"
                            placeholder={`Nombre de la Lista para el Candidato ${index + 1}`}
                            value={candidato.nombreLista}
                            onChange={(e) => {
                                const nuevosCandidatos = [...candidatos];
                                nuevosCandidatos[index].nombreLista = e.target.value;
                                setCandidatos(nuevosCandidatos);
                            }}
                        />
                        <button
                            className="bg-red-500 hover:bg-red-600 text-white px-5 py-2 rounded ml-2"
                            onClick={() => quitarCandidato(index)}
                            disabled={candidatos.length === 2}
                        >
                            Eliminar
                        </button>
                    </div>
                ))}
            </div>
            {/*Por último tenemos el botón de guardar donde se va a aejecutar las funciones anteriormente descritas*/}
            <div className="ml-7 mt-5 pb-10">
                <button
                    className="bg-indigo-600 hover:bg-indigo-800 text-white px-5 py-2 rounded"
                    onClick={guardarEdicion}
                >
                    Guardar Edición
                </button>
            </div>
        </div>
    );
}

export default EditarVotacion;