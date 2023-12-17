package com.example.votos.ctrl;

import com.example.votos.srv.CandidatoSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController // Marca una clase como tipo Controlador para recibir peticiones HTTP.
@RequiredArgsConstructor    // Crea un constructor con los atributos tipo final declarados en la clase.
@RequestMapping(value = "/api/candidato")   // Mapea todos los endpoints que se encuentren dentro de la clase.
public class CandidatoCtrl {
    private final CandidatoSrv candidatoSrv;    // Declaramos una variable de tipo CandidatoSrv.
    //http:localhost:8080/api/persona/save
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> saveCandidato(@RequestBody Map<String, Object> params) {
//        var response = new HashMap<String, Object>();
//        try {
//            response.put("data", candidatoSrv.saveCandidato(params));
//            response.put("msg", "El candidato se almacenados correctamente.");
//            return ResponseEntity.ok().body(response);
//        } catch (Exception e) {
//            response.put("data", "error");
//            response.put("msg", "Ha ocurrido un error al almacenar el candidato");
//            return ResponseEntity.internalServerError().body(response);
//        }
//    }

    @GetMapping(value = "/findAll") // Mapeamos el endpoint para solicitudes HTTP GET.
    public ResponseEntity<Map<String, Object>> findAllByIdVotacion(@RequestParam(name = "code") String code) {  // en la URL de la petición HTTP buscamos un parámetro con nombre code para usarla en nuestro método.
        var response = new HashMap<String, Object>();   // Declaramos un Map para almacenar la respuesta a la petición HTTP.
        try {
            response.put("data", candidatoSrv.findAllByIdVotacion(code));   // Agregamos a la respuesta un elemento con key data y como value la lista de candidatos por id de votación.
            response.put("msg", "Los datos han sido recuperados exitosamente.");    // Agregamos a la respuesta un elemento con key msg y value un mensaje indicando que los candidatos se han recuperado.
            return ResponseEntity.ok().body(response); // Retornamos un objeto de tipo ResponseEntity indicando que la petición fue correcta y en el body de la respuesta agregamos el response que generamos anteriormente.
        } catch (Exception e) { // En caso de que ocurra un error lo capturamos y lo tratamos.
            response.put("data", "error");  // Agregamos a la respuesta un elemento con key data y value error.
            response.put("msg", "Ha ocurrido un error al recuperar los datos: findAllByIdVotacion - Candidato");    // Agregamos a la respuesta un elemento con key msg y value un mensaje indicando que ocurrió un error al recuperar la lista de candidatos de esa votación.
            return ResponseEntity.internalServerError().body(response); // Retornamos un ResponseEntity indicando que hubo un internal server error y agregamos la respuesta al body de nuestro response.
        }
    }

    @GetMapping(value = "/inc") // Mapeamos el endpoint para solicitudes HTTP GET.
    public ResponseEntity<Map<String, Object>> incrementVote(@RequestParam(name = "id") String id) {    // en la URL de la petición HTTP buscamos un parámetro con nombre id para usarla en nuestro método.
        var response = new HashMap<String, Object> ();  // Declaramos un Map para almacenar la respuesta a la petición HTTP.
        try {
            response.put("data", candidatoSrv.incrementVote(id));   // Agregamos a la respuesta un elemento con key data y como value el candidato al cual se incrementó el voto.
            response.put("msg", "El voto ha sido asignado exitosamente.");  // Agregamos a la respuesta un elemento con key msg y value un mensaje indicando que el vo0to ha sido asignado correctamente.
            return ResponseEntity.ok().body(response);  // Retornamos un objeto de tipo ResponseEntity indicando que la petición fue correcta y en el body de la respuesta agregamos el response que generamos anteriormente.
        } catch (Exception e) { // En caso de que ocurra un error lo capturamos y lo tratamos.
            response.put("data", "error");  // Agregamos a la respuesta un elemento con key data y value error.
            response.put("msg", "Ha ocurrido un error al asignar el voto.");    // Agregamos a la respuesta un elemento con key msg y value un mensaje indicando que ocurrió un error al asignar el voto.
            return ResponseEntity.internalServerError().body(response); // Retornamos un ResponseEntity indicando que hubo un internal server error y agregamos la respuesta al body de nuestro response.
        }
    }

    @GetMapping(value = "/eliminarCandidato")   // Mapeamos el endpoint para solicitudes HTTP GET.
    public ResponseEntity<Map<String, Object>> eliminarCandidato(@RequestParam(name = "id") String id) {    // en la URL de la petición HTTP buscamos un parámetro con nombre code para usarla en nuestro método.
        var response = new HashMap<String, Object>();   // Declaramos un Map para almacenar la respuesta a la petición HTTP.
        try {
            response.put("data", candidatoSrv.eliminarCandidato(id));   // Agregamos a la respuesta un elemento con key data y como value el candidato que se elimino.
            response.put("msg", "El candidato ha sido eliminado exitosamente.");    // Agregamos a la respuesta un elemento con key msg y value un mensaje indicando que el candidato se ha eliminado.
            return ResponseEntity.ok().body(response);  // Retornamos un objeto de tipo ResponseEntity indicando que la petición fue correcta y en el body de la respuesta agregamos el response que generamos anteriormente.
        } catch(Exception e) {  // En caso de que ocurra un error lo capturamos y lo tratamos.
            response.put("data", "error");  // Agregamos a la respuesta un elemento con key data y value error.
            response.put("msg", "Ha ocurrido un error al eleiminar el candidato."); // Agregamos a la respuesta un elemento con key msg y value un mensaje indicando que ocurrió un error al eliminar el candidato.
            return ResponseEntity.internalServerError().body(response); // Retornamos un ResponseEntity indicando que hubo un internal server error y agregamos la respuesta al body de nuestro response.
        }
    }
}
