package com.example.votos.dao;

import com.example.votos.dto.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Marca la clase como un componente de acceso a los datos de MySQL.
public interface LoginDao extends JpaRepository<Login, Long> {  // extendemos a la clase JpaRepository para poder manejar las operaciones con la base de datos de la clase Login.
}
