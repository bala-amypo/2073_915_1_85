package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // In a real app, move this to application.properties
    private final String jwtSecret = "yourSecretKeyGeneratedForExamplePurposesOnly123456";
    private final int jwtExpirationInMs = 3600000; // 1 hour
    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    // This is the method your Filter was missing!
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            // Log the specific error if needed
            return false;
        }
    }
}