package com.example.votos.srv;

import com.example.votos.dao.CandidatoDao;
import com.example.votos.dto.Candidato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//Servicio que maneja la lógica de negocio relacionada con los candidatos en una votación.
@Service // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor // Genera automáticamente el constructor con los campos finales inyectados.
public class CandidatoSrv {
    private final CandidatoDao candidatoDao; // Acceso a la capa de datos para los candidatos.

    //Recupera todos los candidatos asociados a una votación específica.
    public List<Candidato> findAllByIdVotacion(String idVotacion) {
        return candidatoDao.findAllByIdVotacion(idVotacion);
    }

    //Busca y recupera un candidato por su ID.
    public Candidato findFirstById(String id) {
        return candidatoDao.findFirstById(id);
    }

    //Incrementa el número de votos a favor de un candidato y lo guarda en la base de datos.
    public Candidato incrementVote(String id) {
        Candidato candidato = this.findFirstById(id);
        candidato.setVotosFavor(candidato.getVotosFavor() + 1);
        return candidatoDao.save(candidato);
    }

    //Elimina un candidato por su ID.
    public Candidato eliminarCandidato(String id) {
        Candidato candidato = this.findFirstById(id);
        candidatoDao.delete(candidato);
        return candidato;
    }
}
