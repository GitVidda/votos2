//  Este código define un componente funcional de React llamado Error. Toma una prop llamada children y devuelve un elemento JSX que muestra un mensaje de error con un color de fondo rojo, texto blanco y algunos estilos. El mensaje de error se pasa como la prop children y se renderiza dentro de un elemento div.
const Error = ({ children }) => {
    return (
        <div className='bg-red-500 text-center uppercase text-white p-3 rounded-md mb-3 font-bold'>
            {children}
        </div>
    )
}

export default Error