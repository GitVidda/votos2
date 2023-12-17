package com.example.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//Clase que representa la entidad "Candidato" para almacenar información sobre los candidatos en una votación.
@Data   // Anotacion nos ayuda a que se cree los métodos getter y setter de nuestra clase
@AllArgsConstructor // Constructor con todos los argumentos.
@NoArgsConstructor // Constructor sin argumentos.
@Builder // Patrón de diseño para crear instancias de objetos de manera más concisa.
public class Candidato {
    @Id // Anotación que indica que este campo es el ID en la base de datos.
    private String id; // ID único del candidato.
    private String idVotacion; // ID de la votación a la que pertenece el candidato.
    private String nombre; // Nombre del candidato.
    private String apellido; // Apellido del candidato.
    private String numLista; // Número de la lista a la que pertenece el candidato.
    private String nombreLista;  // Nombre de la lista a la que pertenece el candidato.
    @Builder.Default // Valor predeterminado para el campo.
    private int votosFavor = 0; // Contador de votos a favor del candidato (valor predeterminado: 0).
}
