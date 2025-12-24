package com.example.demo.exception;

public class BadRequestException extends RuntimeException { // [cite: 216]
    public BadRequestException(String message) {
        super(message);
    }
}