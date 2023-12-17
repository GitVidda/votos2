package com.example.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//Clase que representa la entidad "Voto" para almacenar información sobre los resultados de una votación.
@Data  // Anotación que genera automáticamente los métodos getter y setter.
@NoArgsConstructor   // Constructor sin argumentos.
@AllArgsConstructor  // Constructor con todos los argumentos.
@Builder  // Patrón de diseño para crear instancias de objetos de manera más concisa.
public class Voto {
    @Id // Anotación que indica que este campo es el ID en la base de datos.
    private String id; // ID único del registro de voto.
    private String idVotacion;  // ID de la votación a la que se refiere el voto.
    @Builder.Default  // Valor predeterminado para el campo.
    private int validos = 0;  // Cantidad de votos válidos (valor predeterminado: 0).
    @Builder.Default  // Valor predeterminado para el campo.
    private int nulos = 0;  // Cantidad de votos nulos (valor predeterminado: 0).
    @Builder.Default  // Valor predeterminado para el campo.
    private int blancos = 0;  // Cantidad de votos en blanco (valor predeterminado: 0).
}
