package com.example.votos.srv;

import com.example.votos.dao.ListaDao;
import com.example.votos.dao.LoginDao;
import com.example.votos.dto.Lista;
import com.example.votos.dto.Login;
import com.example.votos.dto.User;
import com.example.votos.dto.Voto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


//Servicio que maneja la lógica de negocio relacionada con el registro de inicio de sesión.
@Service  // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor  // Genera automáticamente el constructor con los campos finales inyectados.
public class LoginSrv {
    private final LoginDao loginDao; // Acceso a la capa de datos para los registros de inicio de sesión.

    //Guarda un registro de inicio de sesión en la base de datos.
    public Login saveLogin(User user) {
        Login login = new Login(); // Crea una nueva instancia de Login.
        login.setUsuario(user.getUser()); // Establece el usuario en el registro de inicio de sesión.
        return loginDao.save(login); // Guarda el registro de inicio de sesión en la base de datos.
    }

    public List<Login> findAll() {
        return loginDao.findAll();
    }
}
