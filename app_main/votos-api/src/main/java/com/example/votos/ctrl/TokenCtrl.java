package com.example.votos.ctrl;

import com.example.votos.srv.TokenSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/token")
public class TokenCtrl {
    private final TokenSrv tokenSrv;

    @GetMapping
    public ResponseEntity<Map<String, Object>> verifyToken(@RequestParam(value = "token") String token) {
        var response = new HashMap<String, Object>();
        try {
            response.put("data", tokenSrv.verifyToken(token));
            response.put("msg", "El token es válido.");
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            response.put("data", "error");
            response.put("msg", "El token no es válido.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
