package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException { // [cite: 208]
    public ResourceNotFoundException(String message) {
        super(message); // [cite: 208]
    }
}