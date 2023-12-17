package com.example.votos.dao;

import com.example.votos.dto.Candidato;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // Marca la clase como un componente de acceso a los datos de mongo.
public interface CandidatoDao extends MongoRepository<Candidato, String> {  // extendemos a la clase MongoRepository para poder manejar las operaciones con la base de datos de la clase Candidato.
    List<Candidato> findAllByIdVotacion(String idVotacion); // Declaramos un método para obtener los candidatos por id de votación.

    Candidato findFirstById(String id); // Declaramos un método para obtener un candidato por su id.

}
