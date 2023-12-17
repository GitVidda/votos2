package com.example.votos.srv;

import com.example.votos.dao.PersonaDao;
import com.example.votos.dao.VotoDao;
import com.example.votos.dto.Persona;
import com.example.votos.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

//Servicio que maneja la lógica de negocio relacionada con las personas y votantes.
@Service  // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor  // Genera automáticamente el constructor con los campos finales inyectados.
public class PersonaSrv {
    final PersonaDao personaDao; // Acceso a la capa de datos para las personas y votantes.
    final VotoDao votoDao; // Acceso a la capa de datos para los votos.

    //Guarda los datos de una persona en la base de datos.
    public Persona savePerson(Map<String, Object> params) {
        Persona person = personaDao.findFirstByIdentificationCardAndCode(params.get("identificationCard").toString(), params.get("code").toString());
        if (person == null) {
            person = new Persona();
            person.setFirstName(params.get("firstName").toString());
            person.setLastName(params.get("lastName").toString());
            person.setIdentificationCard(params.get("identificationCard").toString());
            person.setAddress(params.get("address").toString());
            person.setPhoneNumber(params.get("phoneNumber").toString());
            person.setGender(params.get("gender").toString());
            person.setEmail(params.get("email").toString());
            person.setCode(params.get("code").toString());
            personaDao.save(person); // Guarda la persona en la base de datos.
            return person; // Devuelve la persona guardada.
        } else {
            throw new CustomException("La persona ya ha votado en la votación con code " + params.get("code").toString());
        }
    }

}
