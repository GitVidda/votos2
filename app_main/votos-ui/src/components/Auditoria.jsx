import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";


function Auditoria() {
    const [search, setSearch] = useState('');
    const [listaAudit, setListaAudit] = useState([]);

    useEffect(() => {
        fetchData(); // Llama a la función fetchData() para obtener los datos iniciales
    }, []);

    const fetchData = () => {
        axios.get("http://localhost:8080/api/report").then(response => {
            setListaAudit(response.data.data);
        }).catch(error => {
            console.log(error);
        })
    }

    return(
        <div className="min-h-screen flex flex-col justify-between">
            <header className="bg-indigo-400 p-4 flex justify-between items-center">
                <div className="text-white font-bold text-2xl">VoxChoice: Tu Voz, Tu Elección</div>
            </header>

            {/* Lista de votaciones que se han creado, donde se va a mostrar varios elementos como el nombre, ID, y la fecha de cuando se creo la votación*/}
            <div className="flex-grow bg-purple-300 p-7 w-6/7 my-4 rounded text-center ml-20 mr-20">
                <input
                    type="text"
                    id="default-input"
                    placeholder="Ingresa el id o el nombre de la votación a buscar"
                    className="bg-white border border-white-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-white-700 dark:border-white-600 dark:placeholder-white-400 dark:text-black dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    onChange={(e) => setSearch(e.target.value)}
                />
                <br />
                {listaAudit.length > 0 ? (
                    <ul>
                        {listaAudit.filter((auditar)=>{
                            return search.toLowerCase() === ''
                                ? auditar : auditar.usuario.toLowerCase().includes(search) ||
                                auditar.id.toString().toLowerCase().includes(search)
                        }).map((auditar) => (
                            // Elemento de la lista de auditorias
                            <div key={auditar.id} className="bg-white shadow-md rounded p-4 mb-4 flex justify-between items-center">
                                <div>ID: {auditar.id}</div>
                                <div>Nombre: {auditar.usuario}</div>
                                <div>Fecha de Registro: {auditar.fecha_login}</div>
                            </div>
                        ))}
                    </ul>
                ) : (
                    // Mensaje si no hay votaciones creadas
                    <p className="text-purple-800 text-lg opacity-40">
                        No existe inicios de sesión
                    </p>
                )}
            </div>
        </div>
    )
}

export default Auditoria;