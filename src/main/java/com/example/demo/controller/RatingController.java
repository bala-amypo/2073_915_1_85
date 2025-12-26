package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    private final RatingService ratingService;
    
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    
    @PostMapping("/generate/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RatingResult> generateRating(@PathVariable Long propertyId) {
        RatingResult rating = ratingService.generateRating(propertyId);
        return ResponseEntity.status(201).body(rating);
    }
    
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {
        RatingResult rating = ratingService.getRating(propertyId);
        return ResponseEntity.ok(rating);
    }
}