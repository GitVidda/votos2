const Error = ({ children }) => {
    return (
        <div className='bg-red-800 text-center uppercase text-white p-3 rounded-md mb-3 font-bold'>
            {children}
        </div>
    )
}

export default Error