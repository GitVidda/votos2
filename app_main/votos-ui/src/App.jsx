import Home from "./components/Home";
import Login from "./components/Login";
import RegistroForm from "./components/RegistroForm";
import Pantalla_Inicio from "./components/Pantalla_Inicio";
import CrearVotacion from "./components/CrearVotacion";
import EditarVotacion from "./components/EditarVotacion";
import Listas from "./components/Listas";
import Resultados from "./components/VerResultados";
import Auditoria from "./components/Auditoria";
// import Resultados from "./components/VerResultados";
import "react-datepicker/dist/react-datepicker.css";
// Importaciones necesarias
import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

// Componente principal App
function App() {
  // Estados para almacenar usuarios y votaciones
  const [usuarios, setUsuarios] = useState([]);
  const [votacionOriginal, setVotaciones] = useState([]);
  const [passUser, setPassUser] = useState(null);

  // Manejador para registrar un nuevo usuario
  const handleRegistro = (usuario) => {
    setUsuarios([...usuarios, usuario]);
  };

  // Función para eliminar una votación por su ID
  const eliminarVotacion = (votacionId) => {
    const nuevasVotaciones = votacionOriginal.filter(votacion => votacion.id !== votacionId);
    setVotaciones(nuevasVotaciones);
  };

  // Función para editar una votación (no se utiliza en el código actual)
  const editarVotacion = (votacionEditada) => {     
    return votacionEditada;
  };

  // Renderización de componentes usando React Router
  return (
    <Router>
      <div className="container mx-auto mt-16">
        <Routes>
          {/* Definición de rutas y componentes */}
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login onRegistro={handleRegistro} setPassUser={setPassUser}/>} />
          <Route path="/register" element={<RegistroForm onRegistro={handleRegistro} />} />
          <Route path="/Pantalla_Inicio/:iduser" element={<Pantalla_Inicio votacionOriginal={votacionOriginal} onEliminarVotacion={eliminarVotacion} passUser={passUser}/>} />
          <Route path="/CrearVotacion/:iduser" element={<CrearVotacion/>} />
          <Route path="/EditarVotacion/:votacionId/:iduser" element={<EditarVotacion votacionOriginal={votacionOriginal} onVotacionEditada={editarVotacion} />} />
          <Route path="/listas/:code" element={<Listas />} />
          <Route path="/results/:code" element={<Resultados />} />
          <Route path="/Auditoria" element={<Auditoria />} />
          {/* <Route path="/resultados/:code" element={<Resultados />} /> */}
        </Routes>
      </div>
    </Router>
  );
}

// Exportar el componente App
export default App;
