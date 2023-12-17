import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Error from "./Error"

const Login = ({ onRegistro , setPassUser}) => {
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState(false);
  const [mostrarRegistro, setMostrarRegistro] = useState(false);
  const [registroExitoso, setRegistroExitoso] = useState(false);
  const navigate = useNavigate();

  const [error, setError] = useState(false)

  const handleRegistro = (usuario) => {
    onRegistro(usuario);
    setUser("");
    setPassword("");
    setMostrarRegistro(false);
    setRegistroExitoso(true); 
  };

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get("token");
    if (token !== null) {
      verifyToken(token);
    }
    console.log("token", token);
  }, []);

  const verifyToken = (token) => {
    axios.get("http://localhost:8080/api/token", { params: { token: token}}).then(response => {
      localStorage.setItem("authToken", token);
      setPassUser(response.data.data.user)
      navigate(`/Pantalla_Inicio/${response.data.data.id}`);
    }).catch(error => {
      console.log(error);
    })
  }

  const redirectApp2 = () => {
    window.location.href = "http://localhost:3001"
  }

  const handleLogin = (e) => {
    e.preventDefault();
    if ([user, password].includes('')
      || /\s/.test(user) || /\s/.test(password)) {
      setError(true)
      return
    }
    setError(false)

    let usuario = {
      user: user,
      password: password,
    };
    console.info(usuario);
    axios.post("http://localhost:8080/api/user/verify", usuario).then(response => {
      console.log(response);
      if (response.data.data != null) {
        setPassUser(response.data.data.user)
        navigate(`/Pantalla_Inicio/${response.data.data.id}`);
      }
    }).catch(error => {
      console.log(error);
    })
  };
  
  return (
    <div className="flex items-center justify-center h-screen">
      <form
        className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
        /*onSubmit={handleLogin}*/
      >
        <div className="mb-4">
          {error && <Error>
            <p>Campos inválidos</p>
          </Error>}

          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="usuario"
          >
            Usuario
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="usuario"
            type="text"
            placeholder="nombre de usuario"
            value={user}
            onChange={(e) => setUser(e.target.value)}
            required
          />
        </div>
        <div className="mb-6">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="password"
          >
            Contraseña
          </label>
          <input
            className="shadow appearance-none border border-red rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
            id="password"
            type="password"
            placeholder="*********"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <p className="text-red-500 text-xs italic">
            Por favor ingrese su contraseña.
          </p>
        </div>
        {loginError && (
          <p className="text-red-500 text-xs italic">
            Credenciales incorrectas. Por favor, inténtalo de nuevo.
          </p>
        )}
        {registroExitoso && (
          <p className="text-green-500 text-sm mb-4">
            Registro exitoso. Ahora puedes iniciar sesión con tu cuenta.
          </p>
        )}
        <button
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full mb-4"
        onClick={handleLogin}>Iniciar Sesión</button>
        <button
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full mb-4"
        onClick={redirectApp2}>Iniciar Sesión con App 2</button>
        <div>
        
          <Link
            to="/register"
            className="text-sm text-blue-500 hover:text-blue-700"
            onClick={() => setMostrarRegistro(true)}
          >
            ¿Aún no tienes una cuenta? Registrarse
          </Link>
        </div>
        {mostrarRegistro && (
          <div className="mt-4">
            <h2 className="text-2xl font-semibold mb-2">Registro</h2>
            <RegistroForm onRegistro={handleRegistro} />
          </div>
        )}
      </form>
    </div>
  );
}

export default Login;
