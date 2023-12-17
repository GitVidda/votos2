package com.example.votos.dao;

import com.example.votos.dto.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Marca la clase como un componente de acceso a los datos de mongo.
public interface PersonaDao extends MongoRepository<Persona, String> {  // extendemos a la clase MongoRepository para poder manejar las operaciones con la base de datos de la clase Persona.
    Persona findFirstById(String id);   // Declaramos un método para obtener la persona por su id.
    Persona findFirstByIdentificationCard(String identificationCard);   // Declaramos un método para obtener la persona por su número de cédula.
    Persona findFirstByIdentificationCardAndCode(String identificationCard, String code);   // Declaramos un método para buscar una persona votante por cedula y codigo de votación.
}
