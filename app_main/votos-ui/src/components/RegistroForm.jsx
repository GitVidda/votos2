import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Error from "./Error.jsx";

const RegistroForm = ({ onRegistro }) => {
  const navigate = useNavigate();
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
  const [registroExitoso, setRegistroExitoso] = useState(false);

  const [error, setError] = useState(false)

  const handleRegistroClick = (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      alert("Las contraseñas no coinciden. Por favor, inténtalo de nuevo.");
      return;
    } else if ([user, firstName, lastName, address, phoneNumber, email].includes('')
        || /^\s*$/.test(user) || /^\s*$/.test(firstName) || /^\s*$/.test(lastName) || /^\s*$/.test(address) || /^\s*$/.test(phoneNumber) || /^\s*$/.test(email)
        || !/^[A-Za-z\s]*$/.test(firstName) || !/^[A-Za-z\s]*$/.test(lastName) || !/^[A-Za-z\s]*$/.test(address) || /\s/.test(user)
        || !/^[0-9]+$/.test(phoneNumber)
        || !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)
    ) {
      setError(true)
      return
    }

    setError(false)

    const nuevoUsuario = {
      user,
      password,
      firstName,
      lastName,
      address,
      phoneNumber,
      email,
    };

    axios.post(`http://localhost:8080/api/user`, nuevoUsuario).then(response => {
      console.log(response);
      onRegistro(nuevoUsuario);
      setRegistroExitoso(true);
      navigate("/");
    }).catch(error => {
      alert(error.response.data.msg1);
    })

  };

  return (
    <div className="flex items-center justify-center h-screen">
      <form onSubmit={handleRegistroClick} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div className="mb-4">
          {error && <Error>
            <p>Campos inválidos</p>
          </Error>}

          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="user"
          >
            Nombre de Usuario
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="user"
            type="text"
            placeholder="nombre de usuario"
            value={user}
            onChange={(e) => setUser(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="firstName"
          >
            Nombre
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="firstName"
            type="text"
            placeholder="nombre"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="lastName"
          >
            Apellido
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="lastName"
            type="text"
            placeholder="apellido"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="address"
          >
            Direccion
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="address"
            type="text"
            placeholder="direccion"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="phoneNumber"
          >
            Numero Telefonico
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="phoneNumber"
            type="tel"
            placeholder="numero telefonico"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="email"
          >
            Email
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="email"
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
            Contraseña
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="password"
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="confirmPassword">
            Confirmar Contraseña
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="confirmPassword"
            type="password"
            placeholder="Confirmar contraseña"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <button
          type="submit"
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full mb-4"
        >
          Registrarse
        </button>
        {registroExitoso && (
          <p className="text-green-500 text-sm mb-4">
            Registro exitoso. Ahora puedes iniciar sesión con tu cuenta.
          </p>
        )}
      </form>
    </div>
  );
};

export default RegistroForm;
