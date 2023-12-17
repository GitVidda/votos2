package com.example.votos.srv;

import com.example.votos.dao.UserDao;
import com.example.votos.dto.User;
import com.example.votos.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.Map;

//Servicio que maneja la lógica de negocio relacionada con los usuarios.
@Service  // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor  // Genera automáticamente el constructor con los campos finales inyectados.
public class UserSrv {
    private final UserDao userDao; // Acceso a la capa de datos para los usuarios.
    private final LoginSrv loginSrv; // Acceso al servicio de registro de inicio de sesión.

    public User saveUser(Map<String, Object> params) {
        User user = userDao.findFirstByUser(params.get("user").toString());
        if(user == null) {
            user = new User();
            user.setFirstName(params.get("firstName").toString());
            user.setLastName(params.get("lastName").toString());
            user.setUser(params.get("user").toString());
            user.setPassword(Base64Coder.encodeString(params.get("password").toString()));
            user.setAddress(params.get("address").toString());
            user.setPhoneNumber(params.get("phoneNumber").toString());
            user.setEmail(params.get("email").toString());
            return userDao.save(user); // Guarda el usuario en la base de datos y lo devuelve.
        } else {
            throw new CustomException("El usuario ya existe.");
        }

    }

    //Verifica las credenciales de inicio de sesión de un usuario.
    public User verifyLogin(Map<String, Object> params) {
        User user = userDao.findFirstByUser(params.get("user").toString()); // Busca el usuario por nombre de usuario.
        if (user != null) { // Si el usuario existe.
            if (params.get("password").toString().equals(Base64Coder.decodeString(user.getPassword()))) {
                // Si la contraseña es válida (después de decodificarla), se registra el inicio de sesión.
                loginSrv.saveLogin(user);
                return user; // Devuelve el usuario autenticado.
            }
        }
        return null;  // Si no se autentica, devuelve null.
    }
}
