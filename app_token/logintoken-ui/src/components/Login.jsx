import { useState } from "react"
import Error from "./Error"
import axios from "axios"

const Login = () => {
    const [user, setUser] = useState('')
    const [password, setPassword] = useState('')

    const [error, setError] = useState(false)

    // Se define una función llamada handleSubmit que se activa cuando se envía un formulario. Valida las entradas del formulario (usuario y contraseña) y, si están vacías, muestra un error. Si las entradas no están vacías, crea un objeto con los valores de usuario y contraseña y llama a una función llamada submitData con ese objeto como argumento.
    const handleSubmit = (e) => {
        e.preventDefault();

        // Validación del Formulario
        if ([user, password].includes('')
            || /\s/.test(user) || /\s/.test(password)
        ) {
            setError(true);
            return;
        }

        setError(false);

        // Objeto User
        const objetoUser = {
            user,
            password
        }

        submitData(objetoUser)
    }
    //  Dentro de la función submitData, extrae las propiedades de usuario y contraseña del parámetro objeto. A continuación, realiza una petición HTTP GET a http://localhost:8080/ utilizando axios, pasando el usuario y la contraseña como parámetros de consulta.
    const submitData = (objeto) => {

        axios.post("http://localhost:8290/api/user", objeto).then(response => {
            const token = response.data.data;
            window.location.href = `http://localhost:3000/Login?token=${token}`
            console.log(response)
        }).catch(error => {
            console.log(error)
        })

    }

    return (
        <div className="container mx-auto mt-20">
            <div className="">
                <h1 className="text-center font-bold text-3xl mb-3">Inicio de Sesión</h1>
                <h2 className="text-center text-indigo-700 text-2xl font-semibold mb-7">VoxChoice</h2>

                <form
                    className="bg-white shadow-md rounded-lg py-10 px-5 mb-10 justify-center"
                    onSubmit={handleSubmit} >
                    <div className="mb-5">
                        {error && <Error>
                            <p>Todos los campos son obligatorios</p>
                        </Error>}
                        <label htmlFor="user" className="block font-semibold text-indigo-500 text-xl">Usuario</label>
                        <input id="user" type="text" placeholder="Ingresa tu usuario"
                            className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                            value={user}
                            onChange={(e) => setUser(e.target.value)} />
                    </div>
                    <div className="mb-5">
                        <label htmlFor="password" className="block font-semibold text-indigo-500 text-xl">Contraseña</label>
                        <input id="password" type="password" placeholder="Ingresa tu contraseña"
                            className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className="flex justify-center items-center">
                        <input
                            type="submit"
                            value="Enviar"
                            className="bg-indigo-600 p-3 text-white uppercase 
                                font-bold hover:bg-indigo-700 cursor-pointer transition-all rounded"
                        />
                    </div>
                </form>

            </div>
        </div>
    )
}

export default Login