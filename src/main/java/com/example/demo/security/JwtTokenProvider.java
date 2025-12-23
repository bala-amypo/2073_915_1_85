package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret:RealEstateSecretKey}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs:86400000}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Legacy syntax
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        // Use .parser() instead of .parserBuilder()
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            // Use .parser() instead of .parserBuilder()
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // log signature error
        } catch (MalformedJwtException ex) {
            // log malformed error
        } catch (ExpiredJwtException ex) {
            // log expired error
        } catch (UnsupportedJwtException ex) {
            // log unsupported error
        } catch (IllegalArgumentException ex) {
            // log empty error
        }
        return false;
    }
}