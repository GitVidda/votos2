package com.example.tokenapi.dao;

import com.example.tokenapi.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Marca la clase como un componente de acceso a los datos de mongo.
public interface UserDao extends MongoRepository<User, String> {    // extendemos a la clase MongoRepository para poder manejar las operaciones con la base de datos de la clase User.
    User findFirstByUser(String user);  // Declaramos un método para obtener un usuario por su user (username).
}
