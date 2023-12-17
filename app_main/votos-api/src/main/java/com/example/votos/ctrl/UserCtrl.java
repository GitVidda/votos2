package com.example.votos.ctrl;

import com.example.votos.srv.LoginSrv;
import com.example.votos.srv.UserSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//Controlador que maneja las solicitudes relacionadas con la entidad "Usuario".
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserCtrl {
    private final UserSrv userSrv;
    //Maneja las solicitudes POST para registrar un nuevo usuario.

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody Map<String, Object> params) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", userSrv.saveUser(params)); // Llama al servicio para registrar el usuario y guarda el resultado en "data".
            response.put("msg", "El usuario ha sido registrado exitosamente.");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch (Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error al registrar el usuario.");
            response.put("msg1", e.getMessage());
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }

    //Maneja las solicitudes POST para verificar el inicio de sesión de un usuario.
    @PostMapping(value = "/verify")
    public ResponseEntity<Map<String, Object>> verifyLogin(@RequestBody Map<String, Object> params) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", userSrv.verifyLogin(params)); // Llama al servicio para verificar el inicio de sesión y guarda el resultado en "data".
            response.put("msg", "El usuario existe.");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch (Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Usuario y/o contraseña inválidos.");
            return ResponseEntity.internalServerError().body(response);  // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }
}
