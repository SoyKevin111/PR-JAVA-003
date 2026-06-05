package com.example.demo.auth;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

  private final String secretKey;
  private final Key key;

  public JwtUtil(@Value("${jwt.secret}") String secretKey) {
    this.secretKey = secretKey;
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String generateToken(Usuario usuario) {
    return Jwts.builder()
        .setSubject(usuario.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(Date.from(
            Instant.now()
                .plus(30, ChronoUnit.MINUTES)))
        .signWith(key) // firmar
        .compact();
  }

  public Claims extractClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey.getBytes()) // verificar la firma
        .build()
        .parseClaimsJws(token)// verifica
        .getBody();
  }
}
