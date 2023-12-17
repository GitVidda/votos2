package com.example.tokenapi.srv;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenSrv {
    @Value("${jwt.secret-key}")
    private String SECRET_TOKEN;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    public String generateToken (String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_TOKEN)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_TOKEN)
                .parseClaimsJws(token)
                .getBody();
    }
}
