package br.gov.mt.apiseplag.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    // Defina uma chave secreta fixa para ser usada em todo o ciclo de vida da aplicação
    private static final String SECRET_KEY = "minhaChaveSuperSeguraQueTemMaisDe256Bits"; // Deve ser uma string com pelo menos 256 bits (32 caracteres ASCII)
    public String generateToken(UserDetails userDetails) {
        long expirationTime = 1000 * 60 * 5;  // 5 minutos em milissegundos
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 10 horas
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)  // Usando a mesma chave
                .compact();
    }
}
