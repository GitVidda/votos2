package com.example.votos.srv;

import com.example.votos.dao.CandidatoDao;
import com.example.votos.dao.VotacionDao;
import com.example.votos.dto.Candidato;
import com.example.votos.dto.Votacion;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service  // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor  // Genera automáticamente el constructor con los campos finales inyectados.
public class VotacionSrv {
    private final VotacionDao votacionDao;  // Acceso a la capa de datos para las votaciones.
    private final CandidatoDao candidatoDao; // Acceso a la capa de datos para los candidatos.

    //Guarda o actualiza una votación junto con sus candidatos asociados en la base de datos.
    public Votacion saveVotacion(Map<String, Object> params) {
        Votacion votacion = new Votacion(); // Crea una nueva instancia de Votacion.
        if (!params.containsKey("id")) {
            // Si no se proporciona un ID, crea una nueva votación.
            votacion.setNombre(params.get("nombre").toString());
            votacion.setDescripcion(params.get("descripcion").toString());
            votacion.setIdUser(params.get("iduser").toString());
            votacion.setFechaInicio(this.convertTo(params.get("fechaInicio").toString()));
            votacion.setFechaFinalizacion(this.convertTo(params.get("fechaFinalizacion").toString()));
        } else {
            // Si se proporciona un ID, busca la votación existente y actualiza los detalles.
            votacion = votacionDao.findFirstById(params.get("id").toString());
            votacion.setNombre(params.get("nombre").toString());
            votacion.setDescripcion(params.get("descripcion").toString());
            votacion.setFechaInicio(this.convertTo(params.get("fechaInicio").toString()));
            votacion.setFechaFinalizacion(this.convertTo(params.get("fechaFinalizacion").toString()));
        }
        votacion = votacionDao.save(votacion); // Guarda la votación en la base de datos y la devuelve.
        this.saveCandidatos((List<Map<String, Object>>) params.get("candidatos"), votacion); // Guarda los candidatos asociados.
        return votacion; // Devuelve la votación guardada o actualizada.
    }

    //Recupera todas las votaciones asociadas a un usuario específico.
    public List<Votacion> findAllByIdUser(String id) {
        return votacionDao.findAllByIdUser(id);
    }

    //Guarda los candidatos asociados a una votación en la base de datos.
    public void saveCandidatos(List<Map<String, Object>> candidatos, Votacion votacion) {
        List<Candidato> listaCandidatos = candidatoDao.findAllByIdVotacion(votacion.getId());
        // Elimina los candidatos que ya no están asociados a la votación.
        for (int i = 0; i < candidatos.size(); i++) {
            Candidato candidato = new Candidato();
            if (!candidatos.get(i).containsKey("id")) {
                // Si no hay una clave "id", se crea un nuevo candidato.
                candidato.setIdVotacion(votacion.getId());
                candidato.setNombre(candidatos.get(i).get("nombre").toString());
                candidato.setApellido(candidatos.get(i).get("apellido").toString());
                candidato.setNombreLista(candidatos.get(i).get("nombreLista").toString());
                candidato.setNumLista(candidatos.get(i).get("numLista").toString());
            } else {
                // Si hay una clave "id", se busca el candidato existente y se actualizan sus datos.
                candidato = candidatoDao.findFirstById(candidatos.get(i).get("id").toString());
                candidato.setNombre(candidatos.get(i).get("nombre").toString());
                candidato.setApellido(candidatos.get(i).get("apellido").toString());
                candidato.setNombreLista(candidatos.get(i).get("nombreLista").toString());
                candidato.setNumLista(candidatos.get(i).get("numLista").toString());
                // Se elimina el candidato de la lista de candidatos a ser eliminados.
                listaCandidatos.remove(candidatoDao.findFirstById(candidatos.get(i).get("id").toString()));
                System.out.println();
            }
            this.candidatoDao.save(candidato); // Guarda o actualiza el candidato en la base de datos.
        }
        // Elimina los candidatos restantes que ya no están asociados a la votación.
        for (Candidato cand : listaCandidatos) {
            this.candidatoDao.delete(cand);
        }
    }

    //Convierte una cadena de fecha y hora en un objeto ZonedDateTime.
    public ZonedDateTime convertTo(String date) {
        Instant instant = Instant.parse(date);
        // Crear un objeto ZonedDateTime a partir del Instant y la zona horaria UTC
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        return zonedDateTime;
    }

    //Elimina una votación de la base de datos según su ID.
    public Votacion eliminarVotacion(String id) {
        Votacion votacion = votacionDao.findFirstById(id);
        votacionDao.delete(votacion);
        return votacion;
    }

    //Encuentra y devuelve una votación por su ID.
    public Votacion findFirstById(String id) {
        return votacionDao.findFirstById(id);
    }

    //Verifica si una votación existe en la base de datos según su ID.
    public boolean verifyVotacion(String id) {
        Votacion votacion = votacionDao.findFirstById(id);
        if(votacion != null) {
            return true;
        }
        return false;
    }

}
