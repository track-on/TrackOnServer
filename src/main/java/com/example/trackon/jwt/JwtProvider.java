package com.example.trackon.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.access}")
    private Integer expiredAccess;

    @Value("${jwt.refresh}")
    private Integer expiredRefresh;

    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(Long userId) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredAccess * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("type", "access_token")
                .setSubject(userId.toString())
                .compact();
    }

    public String generateRefreshToken(Long userId) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredRefresh * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("type", "refresh_token")
                .setSubject(userId.toString())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody().getSubject();

            return false;
        }catch (Exception e) {
            return true;
        }
    }

    public boolean isRefreshToken(String token) {
        if(validateToken(token))
            throw new RuntimeException();

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().get("type").equals("refresh_token");
    }

    public Long getUserId(String token) {
        if(validateToken(token))
            throw new RuntimeException();

        return Long.parseLong(Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject());
    }
}
