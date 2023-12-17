package com.example.votos.srv;

import com.example.votos.dao.UserDao;
import com.example.votos.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenSrv {
    private final UserDao userDao;
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    public User verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return userDao.findFirstByUser(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}
