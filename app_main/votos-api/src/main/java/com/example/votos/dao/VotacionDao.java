package com.example.votos.dao;

import com.example.votos.dto.Votacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marca la clase como un componente de acceso a los datos de mongo.
public interface VotacionDao extends MongoRepository<Votacion, String> {    // extendemos a la clase MongoRepository para poder manejar las operaciones con la base de datos de la clase Votacion.
    List<Votacion> findAllByIdUser(String idUser);  // Declaramos un método para obtener el listado de vatciones creadas de un usuario buscando por el idUser.
    Votacion findFirstById(String id);  // Declaramos un método para obtener una votacion por su id.
}
