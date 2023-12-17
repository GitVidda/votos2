package com.example.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

//Clase que representa la entidad "Persona" para almacenar información sobre individuos.
@Data // Anotación que genera automáticamente los métodos getter y setter.
@AllArgsConstructor // Constructor con todos los argumentos.
@NoArgsConstructor // Constructor sin argumentos.
@Builder // Patrón de diseño para crear instancias de objetos de manera más concisa.
public class Persona {
    @Id // Anotación que indica que este campo es el ID en la base de datos.
    private String id;  // ID único de la persona.
    private String firstName; // Primer nombre de la persona.
    private String lastName; // Apellido de la persona.
    private String identificationCard;  // Número de identificación de la persona.
    private String address;  // Dirección de la persona.
    private String phoneNumber;  // Número de teléfono de la persona.
    private String gender;  // Género de la persona.
    private String email;  // Dirección de correo electrónico de la persona.
    @Indexed // Anotación que indica que este campo debe ser indexado para búsquedas rápidas.
    private String code; // Código único asociado a la persona.
    @Builder.Default // Valor predeterminado para el campo.
    private boolean voted = false; // Indicador de si la persona ha votado (valor predeterminado: false).
}
