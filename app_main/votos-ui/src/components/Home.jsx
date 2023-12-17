import { useState } from "react"

import Header from "./Header"
import Formulario from "./Formulario"
import Imagen from "./Imagen"

// Este código define un componente funcional de React llamado Home. Renderiza un div contenedor con un componente Header y dos divs hijos. El primer div hijo contiene un componente Formulario con algunos props que se le pasan, y el segundo div hijo contiene un componente Imagen. El componente también utiliza el hook useState para gestionar las variables de estado votantes y votante.
function Home() {

  const [votantes, setVotantes] = useState([])
  const [votante, setVotante] = useState({})

  return (
    <div className="container mx-auto mt-16">

      <Header />
      <div className="flex mt-16 justify-around">
        <div >
          <Formulario
            votantes={votantes}
            setVotantes={setVotantes}
            votante={votante}
            setVotante={setVotante}
          />
        </div>
        <div className="w-3/6">
          <Imagen />
        </div>
      </div>
    </div>
  )
}

export default Home;
