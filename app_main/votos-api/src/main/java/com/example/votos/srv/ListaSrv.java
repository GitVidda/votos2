package com.example.votos.srv;

import com.example.votos.dao.ListaDao;
import com.example.votos.dao.VotoDao;
import com.example.votos.dto.Lista;
import com.example.votos.dto.Persona;
import com.example.votos.dto.Voto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

//Servicio que maneja la lógica de negocio relacionada con las listas de candidatos.
@Service // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor // Genera automáticamente el constructor con los campos finales inyectados.
public class ListaSrv {
    final ListaDao listaDao; // Acceso a la capa de datos para las listas de candidatos.
    //Guarda una nueva lista de candidatos en la base de datos.
    public Lista saveLista(Map<String, Object> params) {
        Lista lista = new Lista();
        lista.setId(params.get("id").toString());
        lista.setListName(params.get("lastName").toString());
        lista.setListNumber(params.get("listNumber").toString());
        listaDao.save(lista); // Guarda la lista en la base de datos.
        return lista; // Devuelve la lista guardada.
    }
}
