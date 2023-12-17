package com.example.votos.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

//Clase que representa la entidad "User" para almacenar información sobre los usuarios.
@Data  // Anotación que genera automáticamente los métodos getter y setter.
@AllArgsConstructor  // Constructor con todos los argumentos.
@NoArgsConstructor   // Constructor sin argumentos.
@Builder  // Patrón de diseño para crear instancias de objetos de manera más concisa.
public class User {
    @Id // Anotación que indica que este campo es el ID en la base de datos.
    private String id; // ID único del usuario.
     private String user;  // Nombre de usuario.
    private String password;  // Contraseña del usuario.
    private String firstName;  // Primer nombre del usuario.
    private String lastName;  // Apellido del usuario.
    private String address;  // Dirección del usuario.
    private String phoneNumber;  // Número de teléfono del usuario.
    private String email;  // Dirección de correo electrónico del usuario.
    @Builder.Default // Valor predeterminado para el campo.
    private ZonedDateTime registrationDate = ZonedDateTime.now(); // Fecha y hora de registro del usuario (valor predeterminado: la fecha y hora actual).
}
