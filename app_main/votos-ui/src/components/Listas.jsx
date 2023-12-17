import { useState, useEffect } from 'react';
import axios from 'axios';
import picture from '../images/suitable-candidate.256x256.png'
import { useNavigate, useLocation } from "react-router-dom";
import './styles.css';

const Listas = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const pathSegments = location.pathname.split("/");
    const code = pathSegments[pathSegments.length - 1];

    const [candidates, setCandidates] = useState([])

    useEffect(() => {
        fetchData(code);
    }, [])

    const fetchData = () => {
        axios.get('http://localhost:8080/api/candidato/findAll', { params: { code: code } }).then(response => {
            setCandidates(response.data.data);
        }).catch(error => {
            console.log(error);
        })
    };

    const handleVote = (id) => {
        axios.get("http://localhost:8080/api/candidato/inc", {params: {id: id}}).then(response => {
            console.log(response);
            navigate("/");
        }).catch(error => {
            console.log(error);
        })
    };

    return (
        <div>
            {/* <header className="header-container">
                <img src={encabezado} alt="Encabezado" className="header-image" />
            </header> */}
            <div className="flex items-center justify-center">
                <h1 className="mb-4 text-4xl font-extrabold leading-none tracking-tight text-blue-600 md:text-5xl lg:text-6xl dark:text-blue-500">Vota ahora</h1>
            </div>
            <div className="cards">
                {candidates.map((candidate, index) => (
                    <div className="box w-full max-w-xs rounded overflow-hidden shadow-lg" key={index}>
                        <div className="flex flex-col items-center pb-10">
                            <img
                                className="w-24 h-24 mb-3 rounded-full shadow-lg"
                                src={picture}
                                alt="image"
                            />
                            <h4 className="mb-1 text-2xl font-medium text-blue-600 dark:text-cyan-500">
                                {candidate.nombre + " " + candidate.apellido}
                            </h4>
                            <h6 className="mb-1 text-xl font-medium text-blue-600 dark:text-blue-500">
                                {candidate.nombreLista}
                            </h6>
                            <h6 className="mb-1 text-xl font-medium text-blue-600 dark:text-blue-500">
                                {candidate.numLista}
                            </h6>
                            <div className="flex mt-4 space-x-3 md:mt-6">
                                <button
                                    className="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                                    onClick={() => handleVote(candidate.id)}
                                >
                                    Votar
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
};

export default Listas;