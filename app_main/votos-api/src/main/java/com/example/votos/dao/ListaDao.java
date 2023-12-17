package com.example.votos.dao;

import com.example.votos.dto.Lista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Marca la clase como un componente de acceso a los datos de mongo.
public interface ListaDao extends MongoRepository<Lista, String> {  // extendemos a la clase MongoRepository para poder manejar las operaciones con la base de datos de la clase Lista.
}
