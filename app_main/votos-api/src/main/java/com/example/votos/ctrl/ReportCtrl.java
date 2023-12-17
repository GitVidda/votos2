package com.example.votos.ctrl;

import com.example.votos.srv.LoginSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/report")
public class ReportCtrl {
    private final LoginSrv loginSrv;

    @GetMapping
    public ResponseEntity<Map<String, Object>> generateReport() {
        var response = new HashMap<String, Object>();
        try {
            response.put("data", loginSrv.findAll());
            response.put("msg", "El reporte se ha generado con éxito.");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("data", "error");
            response.put("msg", "Ha ocurriodo un error al generar el reporte.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
