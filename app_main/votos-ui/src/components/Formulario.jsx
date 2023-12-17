import { useState } from "react"
import Error from "./Error"
import axios from "axios"
import { useNavigate } from 'react-router-dom';

// Este código define un componente React llamado Formulario, que representa un formulario para recoger información de los votantes. Utiliza el hook useState de React para gestionar los valores de entrada del formulario y realizar un seguimiento de los errores del formulario.
// El componente Formulario muestra un formulario con campos de entrada para recopilar información de los votantes. También muestra mensajes de error basados en los estados form error y codeError.
const Formulario = ({ votantes, setVotantes }) => {
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [identificationCard, setIdentificationCard] = useState('')
    const [gender, setGender] = useState('M')
    const [address, setAddress] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [email, setEmail] = useState('')
    const [code, setCode] = useState('')

    const [error, setError] = useState(false)
    const [codeError, setcodeError] = useState(false);

    // Este código define una función llamada handleSubmit que se activa cuando se envía un formulario. Evita el comportamiento predeterminado de envío de formularios, comprueba si alguno de los campos del formulario está vacío y establece un estado de error en caso de ser afirmativo. Si todos los campos tienen valores, crea un objeto llamado objetoVotante con los valores de los campos del formulario y llama a una función denominada submitData con objetoVotante y una variable de código. A continuación, actualiza una lista de votantes con el nuevo objetoVotante y restablece los campos del formulario a valores vacíos.
    const handleSubmit = (e) => {
        e.preventDefault()

        if ([firstName, lastName, identificationCard, gender, address, phoneNumber, email, code].includes('')
            || /^\s*$/.test(firstName) || /^\s*$/.test(lastName) || /\s/.test(identificationCard) || /^\s*$/.test(address) || /\s/.test(phoneNumber) || /\s/.test(email) || /\s/.test(code)
            || !/^[A-Za-z\s]*$/.test(firstName) || !/^[A-Za-z\s]*$/.test(lastName) || !/^[A-Za-z\s]*$/.test(address)
            || !/^\d{10}$/.test(identificationCard) || !/^[0-9]+$/.test(phoneNumber) || code.length !== 24
            || !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)
        ) {
            setError(true)
            return
        }

        setError(false)

        const objetoVotante = {
            firstName,
            lastName,
            identificationCard,
            gender,
            address,
            phoneNumber,
            email,
            code,
        }

        submitData(objetoVotante, code);

        setVotantes([...votantes, objetoVotante])

        setFirstName('')
        setLastName('')
        setIdentificationCard('')
        setGender('M')
        setAddress('')
        setPhoneNumber('')
        setEmail('')
        setCode('')
    }
    //  Este código define una función denominada submitData que toma un parámetro objeto. Dentro de la función, hace una petición HTTP GET a http://localhost:8080/api/votacion/verify con un código de parámetro de consulta. Si los datos de respuesta contienen un valor verdadero, realiza una petición HTTP POST a http://localhost:8080/api/person con los datos del objeto. Si la respuesta de la solicitud POST es correcta, registra los datos de la respuesta y navega a una nueva URL. Si los datos de respuesta de la solicitud GET inicial son falsos, establece un indicador codeError en true.
    const submitData = (objeto) => {
        axios.get(`http://localhost:8080/api/votacion/verify`, { params: { code: code } }).then(response => {
            let verify = response.data.data;
            console.log("verify", verify);
            if (verify) {
                setcodeError(false);
                axios.post('http://localhost:8080/api/person', objeto).then(response => {
                    console.log(response.data)
                    navigate(`/listas/${code}`);
                }).catch(error => {
                    console.log(error);
                    alert(error.response.data.msg1);
                })
            } else {
                setcodeError(true);
            }
        }).catch(error => {
            console.log(error)
        })

    }

    return (
        <div className="mx-5">
            <h2 className="font-black text-3xl text-center">Datos del Votante</h2>

            <form
                onSubmit={handleSubmit}
                className="bg-white shadow-md rounded-lg py-10 px-5 mb-10 mt-3 w-96">
                <div className="mb-5">

                    {error && <Error>
                        <p>Campos inválidos</p>
                    </Error>}

                    {codeError && <Error>
                        <p>Códido incorrecto</p>
                    </Error>}

                    <label htmlFor="firstName" className="block text-gray-700 uppercase font-bold">
                        Nombre
                    </label>
                    <input
                        id="firstName"
                        type="text"
                        placeholder="Ingresa tu nombre"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                    />
                </div>
                <div className="mb-5">
                    <label htmlFor="lastName" className="block text-gray-700 uppercase font-bold">
                        Apellido
                    </label>
                    <input
                        id="lastName"
                        type="text"
                        placeholder="Ingresa tu apellido"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                    />
                </div>
                <div className="mb-5">
                    <label htmlFor="identificationCard" className="block text-gray-700 uppercase font-bold">
                        C.I
                    </label>
                    <input
                        id="identificationCard"
                        type="text"
                        placeholder="Ingresa tu #cédula"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={identificationCard}
                        onChange={(e) => setIdentificationCard(e.target.value)}
                    />
                </div>
                <div className="mb-5">
                    <div className="flex">
                        <label htmlFor="gender" className="block text-gray-700 uppercase font-bold mr-3">
                            Género:
                        </label>
                        <select id="gender"
                            className="block  uppercase  w-80 mt-2 bg-white -mt-0.5 ml-5"
                            value={gender}
                            onChange={(e) => setGender(e.target.value)}>
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                        </select>
                    </div>
                </div>
                <div className="mb-5">
                    <label htmlFor="address" className="block text-gray-700 uppercase font-bold">
                        Dirección
                    </label>
                    <input
                        id="address"
                        type="text"
                        placeholder="Ingresa tu dirección"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={address}
                        onChange={(e) => setAddress(e.target.value)}
                    />
                </div>
                <div className="mb-5">
                    <label htmlFor="phoneNumber" className="block text-gray-700 uppercase font-bold">
                        Teléfono
                    </label>
                    <input
                        id="phoneNumber"
                        type="text"
                        placeholder="Ingresa tu teléfono"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                    />
                </div>
                <div className="mb-5">
                    <label htmlFor="email" className="block text-gray-700 uppercase font-bold">
                        Email
                    </label>
                    <input
                        id="email"
                        type="email"
                        placeholder="Ingresa tu email"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="mb-5">
                    <label htmlFor="code" className="block text-gray-700 uppercase font-bold">
                        Código de votación
                    </label>
                    <input
                        id="code"
                        type="text"
                        placeholder="Ingresa el código de votación"
                        className="border-2 w-full p-2 mt-2 placeholder-gray-400 rounded-md"
                        value={code}
                        onChange={(e) => setCode(e.target.value)}
                    />
                </div>

                <button
                    className="bg-indigo-600 w-full p-3 text-white uppercase 
                    font-bold hover:bg-indigo-700 cursor-pointer transition-all flex justify-center items-center"
                    value='Votar Ahora'
                    onClick={handleSubmit}
                >Votar Ahora </button>
            </form>
        </div>
    )
}

export default Formulario