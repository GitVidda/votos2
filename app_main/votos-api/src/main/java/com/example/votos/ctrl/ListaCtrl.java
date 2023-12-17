package com.example.votos.ctrl;

import com.example.votos.srv.ListaSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


//Controlador que maneja las solicitudes relacionadas con la entidad "Lista".
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/lista")
public class ListaCtrl {
    private final ListaSrv listaSrv;

    //Maneja las solicitudes POST para almacenar una nueva lista.
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveLista(@RequestBody Map<String, Object> params) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", listaSrv.saveLista(params)); // Llama al servicio para almacenar la lista y guarda el resultado en "data".
            response.put("msg", "Los datos han sido almacenados correctamente."); 
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch (Exception e) {
            response.put("data", "error");// Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error al almacenar la lista.");
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }
}
