import { Link, useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";

function Header() {
  const [error, setError] = useState(false);
  const [codeError, setCodeError] = useState(false);
  const navigate = useNavigate();
  const [modalVisible, setModalVisible] = useState(false);
  const [inputText, setInputText] = useState('');

  const openModal = () => {
    setModalVisible(true);
  }

  const closeModal = () => {
    setModalVisible(false);
  }

  const handleInputChange = (event) => {
    setInputText(event.target.value);
  };

  const submitData = (e) => {
    e.preventDefault();
    axios.get(`http://localhost:8080/api/votacion/verify`, { params: { code: inputText } }).then(response => {
      let verify = response.data.data;
      console.log("verify", verify);
      if (verify) {
        navigate(`/results/${inputText}`);
        setCodeError(false);
      } else {
        setCodeError(true);
      }
    }).catch(error => {
      console.log(error)
    })
  }

  return (
    <div className="flex justify-between">
      <h1 className='font-black text-5xl md:w-2/3 mx-auto'>
        Aplicación de {''}
        <span className="text-blue-700">Votos</span>
      </h1>
      <div className="sm:flex-wrap mr-2">
        <Link to="/Login" className="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-2 
                                    px-4 rounded-full uppercase mr-3 md:mb-3">
          Iniciar sesión
        </Link>
        <button onClick={openModal} className="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded-full uppercase">
          Mostrar Resultados
        </button>
      </div>
      {modalVisible && (
        <div className="fixed top-0 left-0 right-0 z-50 flex items-center justify-center w-full h-full bg-gray-800 bg-opacity-50">
          <div className="bg-white p-6 rounded-lg">
            <h2 className="text-xl font-semibold mb-4">Ingresa un texto</h2>
            <form onSubmit={submitData}>
              <label htmlFor="inputText" className="block mb-2 text-sm font-medium text-gray-900">
                Texto:
              </label>
              <input
                type="text"
                id="inputText"
                value={inputText}
                onChange={(e) => setInputText(e.target.value)}
                className="border border-gray-300 rounded-lg p-2 w-full"
              />
              <div className="mt-4">
                <button
                  type="submit"
                  onClick={submitData}
                  className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full uppercase"
                >
                  Enviar
                </button>
                <button
                  type="button"
                  onClick={closeModal}
                  className="ml-2 border border-gray-300 text-gray-700 font-bold py-2 px-4 rounded-full uppercase"
                >
                  Cancelar
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>

  )
}

export default Header