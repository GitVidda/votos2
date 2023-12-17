package com.example.tokenapi.ctrl;

import com.example.tokenapi.srv.UserSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserCtrl {
    private final UserSrv userSrv;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> params) {
        var response = new HashMap<String, Object>();
        try {
            response.put("data", userSrv.verifyLogin(params));
            response.put("msg", "Usuario iniciado sesión con éxito.");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("data", "error");
            response.put("msg", "Usuario y/o contraseña inválidos.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
