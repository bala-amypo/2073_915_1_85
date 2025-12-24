package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings") // [cite: 340]
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/generate/{propertyId}") // [cite: 342]
    @PreAuthorize("hasRole('ADMIN')") // [cite: 343]
    public ResponseEntity<RatingResult> generateRating(@PathVariable Long propertyId) {
        return ResponseEntity.ok(ratingService.generateRating(propertyId)); // [cite: 344]
    }

    @GetMapping("/property/{propertyId}") // [cite: 345]
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {
        return ResponseEntity.ok(ratingService.getRating(propertyId)); // [cite: 347]
    }
}