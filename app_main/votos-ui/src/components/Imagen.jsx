import picture from '../images/vote.jpg'

// Este código define un componente funcional llamado Imagen
// El código JSX consiste en un elemento div con algunas clases y un elemento img con un atributo src. El valor del atributo src es la variable picture. Se espera que la variable picture contenga la URL de una imagen.
const Imagen = () => {
    return (
        <>
            <div className='mt-40 rounded-2xl bg-indigo-400 py-2 px-2'>
                <img src={picture} alt="imagen votante" className='rounded-2xl' />
            </div>
        </>
    )
}

export default Imagen