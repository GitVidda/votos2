package com.example.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//Clase que representa la entidad "Lista" para almacenar información sobre las listas de candidatos en una votación.
@Data // Anotación que genera automáticamente los métodos getter y setter.
@NoArgsConstructor // Constructor sin argumentos.
@AllArgsConstructor // Constructor con todos los argumentos.
public class Lista {
    @Id // Anotación que indica que este campo es el ID en la base de datos.
    private String id; // ID único de la lista.
    private String listName; // Nombre de la lista de candidatos.
    private String listNumber; // Número de la lista de candidatos.
}
