package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {
    private final FacilityScoreService scoreService;

    public FacilityScoreController(FacilityScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> submitScore(@PathVariable Long propertyId, 
                                                    @Valid @RequestBody FacilityScore score) {
        return ResponseEntity.ok(scoreService.addScore(propertyId, score));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> fetchScore(@PathVariable Long propertyId) {
        return ResponseEntity.ok(scoreService.getScoreByProperty(propertyId));
    }
}