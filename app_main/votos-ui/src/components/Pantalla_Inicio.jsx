import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import axios from "axios";

function PantallaInicio({ votacionOriginal, onEliminarVotacion , passUser}) {
  // Obtén la ubicación actual
  const location = useLocation();
  const pathSegments = location.pathname.split("/");
  // Extrae el id del usuario de la URL
  const iduser = pathSegments[pathSegments.length - 1];
  // Estado para almacenar la lista de votaciones
  const [listavotacion, setListaVotacion] = useState([]);

  const [search, setSearch] = useState('');
  const [audit, setAudit] = useState(false);

  // Se ejecuta al montar el componente
  useEffect(() => {
    fetchData(); // Llama a la función fetchData() para obtener los datos iniciales
      if (passUser === 'admin'){
          setAudit(true)
      }
  }, []);

  // Función para obtener la lista de votaciones desde el servidor
  const fetchData = () => {
    axios.get("http://localhost:8080/api/votacion/findall", { params: { iduser: iduser } })
      .then(response => {
        // Actualiza el estado con los datos recibidos del servidor
        setListaVotacion(response.data.data);
        console.log("response", response);
      })
      .catch(error => {
        console.log("error:", error.msg);
      });
  }

  // Función para eliminar una votación específica por su ID
  const eliminarVotacion = (id) => {
    axios.get("http://localhost:8080/api/votacion/elim", { params: { id: id } })
      .then(response => {
        console.log("response", response);
        fetchData(); // Vuelve a obtener los datos después de eliminar para actualizar la lista
      })
      .catch(error => {
        console.log("error", error);
      });
  }

  return (
    <div className="min-h-screen flex flex-col justify-between">
      <header className="bg-indigo-400 p-4 flex justify-between items-center">
        <div className="text-white font-bold text-2xl">VoxChoice: Tu Voz, Tu Elección</div>
      </header>
      {/* Botones de Crear Votación donde va a llevar al componente que va a permitir crear la votación y Cerrar Sesión
      donde se va a regresar a la página principal de la aplicación */}
      <div className="pt-3 flex justify-between items-center ml-40 mr-40">
        <Link
          to={`/CrearVotacion/${iduser}`}
          className="bg-indigo-600 hover:bg-indigo-800 text-white px-4 py-2 rounded"
        >
          Crear Votación
        </Link>
          {audit && <Link
              to="/Auditoria"
              className="bg-indigo-600 hover:bg-indigo-800 text-white px-4 py-2 rounded"
          >
              Auditoría
          </Link>
          }
        <Link
          to="/"
          className="bg-indigo-600 hover:bg-indigo-800 text-white px-4 py-2 rounded"
        >
          Cerrar Sesión
        </Link>
      </div>
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
        {listavotacion.length > 0 ? (
          <ul>
              {listavotacion.filter((votacion)=>{
                  return search.toLowerCase() === ''
                      ? votacion : votacion.nombre.toLowerCase().includes(search) ||
                      votacion.id.toLowerCase().includes(search)
              }).map((votacion) => (
              // Elemento de la lista de votaciones
              <div key={votacion.id} className="bg-white shadow-md rounded p-4 mb-4 flex justify-between items-center">
                <div>Nombre: {votacion.nombre}</div>
                <div>ID: {votacion.id}</div>
                <div>Fecha de Registro: {votacion.fechaRegistro}</div>
                {/* Botones de Eliminar y Editar */}
                <div className="space-x-2">
                  <button
                    className="bg-red-500 hover:bg-red-600 text-white px-2 py-1 rounded ml-2"
                    onClick={() => eliminarVotacion(votacion.id)}
                  >
                    Eliminar
                  </button>
                  <Link to={`/EditarVotacion/${votacion.id}/${iduser}`} className="bg-blue-600 hover:bg-blue-800 text-white px-2 py-1 rounded ml-2">
                    Editar
                  </Link>
                </div>
              </div>
            ))}
          </ul>
        ) : (
          // Mensaje si no hay votaciones creadas
          <p className="text-purple-800 text-lg opacity-40">
            No se ha creado ninguna votación
          </p>
        )}
      </div>
    </div>
  );
}

export default PantallaInicio;
