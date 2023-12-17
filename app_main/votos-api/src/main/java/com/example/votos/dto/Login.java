package com.example.votos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

//Clase que representa la entidad "Login" para almacenar información sobre los inicios de sesión.
@Data // Anotación que genera automáticamente los métodos getter y setter.
@NoArgsConstructor // Constructor sin argumentos.
@AllArgsConstructor // Constructor con todos los argumentos.
@Builder // Patrón de diseño para crear instancias de objetos de manera más concisa.
@Entity // Indica que esta clase es una entidad JPA.
@Table(name = "login") // Nombre de la tabla en la base de datos.
public class Login {
    @Id // Anotación que indica que este campo es la clave primaria en la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación automática del ID.
    private Long id; // ID único para el registro de inicio de sesión.
    private String usuario; // Nombre de usuario que inició sesión.
    @Builder.Default // Valor predeterminado para el campo.
    private Date fecha_login = new Date(); // Fecha y hora del inicio de sesión (valor predeterminado: la fecha y hora actual).
}
