package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores") // [cite: 329]
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}") // [cite: 331]
    @PreAuthorize("hasRole('ADMIN')") // [cite: 332]
    public ResponseEntity<FacilityScore> addScore(@PathVariable Long propertyId, @RequestBody FacilityScore score) {
        return ResponseEntity.ok(facilityScoreService.addScore(propertyId, score)); // [cite: 334]
    }

    @GetMapping("/{propertyId}") // [cite: 335]
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        return ResponseEntity.ok(facilityScoreService.getScoreByProperty(propertyId)); // [cite: 337]
    }
}