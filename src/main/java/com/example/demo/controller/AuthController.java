package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // [cite: 308]
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register") // [cite: 310]
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getName(), registerRequest.getEmail(), 
                             registerRequest.getPassword(), registerRequest.getRole());
        User result = userService.registerUser(user); // [cite: 312]
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", result));
    }

    @PostMapping("/login") // [cite: 313]
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = userService.findByEmail(loginRequest.getEmail());
        String jwt = tokenProvider.generateToken(authentication, user); // [cite: 315]

        return ResponseEntity.ok(new AuthResponse(jwt, user.getId(), user.getEmail(), user.getRole()));
    }
}