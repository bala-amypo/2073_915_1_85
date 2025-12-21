package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public void generateToken(Authentication authentication) {
    }

    public void validateToken(String token) {
    }

    public void getUserIdFromToken(String token) {
    }
}
