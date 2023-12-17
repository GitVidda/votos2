package com.example.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

//Clase que representa la entidad "Votacion" para almacenar información sobre procesos de votación.
@Data  // Anotación que genera automáticamente los métodos getter y setter.
@AllArgsConstructor  // Constructor con todos los argumentos.
@NoArgsConstructor   // Constructor sin argumentos.
@Builder  // Patrón de diseño para crear instancias de objetos de manera más concisa.
public class Votacion {
    @Id // Anotación que indica que este campo es el ID en la base de datos.
    private String id; // ID único del proceso de votación.
    private String idUser;  // ID del usuario responsable de la votación.
    private String nombre;  // Nombre de la votación.
    private String descripcion;  // Descripción de la votación.
    @Builder.Default // Valor predeterminado para el campo.
    private ZonedDateTime fechaRegistro = ZonedDateTime.now(); // Fecha y hora de registro de la votación (valor predeterminado: la fecha y hora actual).
    private ZonedDateTime fechaInicio;  // Fecha y hora de inicio de la votación.
    private ZonedDateTime fechaFinalizacion;  // Fecha y hora de finalización de la votación.
}
