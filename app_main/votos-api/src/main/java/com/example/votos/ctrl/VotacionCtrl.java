package com.example.votos.ctrl;
import com.example.votos.dto.Votacion;
import com.example.votos.srv.VotacionSrv;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//Controlador que maneja las solicitudes relacionadas con la entidad "Votación".
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/votacion")
public class VotacionCtrl {
    private final VotacionSrv votacionSrv;
    //http:localhost:8080/api/persona/save
    //Maneja las solicitudes POST para almacenar los datos de una nueva votación.
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveVotacion(@RequestBody Map<String, Object> params) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", votacionSrv.saveVotacion(params)); // Llama al servicio para almacenar los datos de la votación y guarda el resultado en "data".
            response.put("msg", "La votacion se almacenados correctamente.");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch (Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error al almacenar la votacion");
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }

    //Maneja las solicitudes GET para buscar todas las votaciones asociadas a un usuario específico.
    @GetMapping(value = "/findall")
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam(name = "iduser") String id) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", votacionSrv.findAllByIdUser(id)); // Llama al servicio para recuperar las votaciones asociadas al usuario y guarda el resultado en "data".
            response.put("msg", "Las votaciones han sido recuperadas exitosamente.");
            return ResponseEntity.ok().body(response);// Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch (Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error: findAll-VotacionCtrl");
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }

    //Maneja las solicitudes GET para eliminar una votación según su ID.
    @GetMapping(value = "/elim")
    public ResponseEntity<Map<String, Object>> eliminar(@RequestParam(name = "id") String id) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", votacionSrv.eliminarVotacion(id)); // Llama al servicio para eliminar la votación y guarda el resultado en "data".
            response.put("msg", "La votación ha sido eliminada exitosamente.");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch (Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error al eliminar la votación.");
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }

    //Maneja las solicitudes GET para buscar una votación según su ID.
    @GetMapping(value = "/find")
    public ResponseEntity<Map<String, Object>> findVotacion(@RequestParam(name = "idVotacion") String idVotacion) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", votacionSrv.findFirstById(idVotacion)); // Llama al servicio para recuperar la votación según su ID y guarda el resultado en "data".
            response.put("msg", "La votacion ha sido recuperada exitosamente");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch(Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error al recuperar los datos.");
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }

    //Maneja las solicitudes POST para editar una votación existente.
    @PostMapping(value = "/edit")
    public ResponseEntity<Map<String, Object>> editarVotacion(@RequestBody Map<String, Object> params) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", votacionSrv.saveVotacion(params)); // Llama al servicio para editar la votación y guarda el resultado en "data".
            response.put("msg", "El elemento ha sido editado con éxito.");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch(Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "Ha ocurrido un error al editar la votación.");
            return ResponseEntity.internalServerError().body(response);  // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }

    @GetMapping(value = "/verify")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestParam(name = "code") String code) {
        var response = new HashMap<String, Object>(); // Mapa para la respuesta al cliente.
        try {
            response.put("data", votacionSrv.verifyVotacion(code)); // Llama al servicio para verificar la existencia de la votación según el código y guarda el resultado en "data".
            response.put("msg", "La votación existe.");
            return ResponseEntity.ok().body(response); // Devuelve una respuesta exitosa con el mapa de respuesta.
        } catch(Exception e) {
            response.put("data", "error"); // Si ocurre un error, marca "data" como "error".
            response.put("msg", "La votación no existe.");
            return ResponseEntity.internalServerError().body(response); // Devuelve una respuesta de error interno con el mapa de respuesta.
        }
    }
}
