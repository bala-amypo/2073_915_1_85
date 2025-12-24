package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RatingLogService;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingResultRepository; // [cite: 279]
    private final FacilityScoreRepository facilityScoreRepository; // [cite: 280]
    private final PropertyRepository propertyRepository; // [cite: 281]
    private final RatingLogService ratingLogService; // [cite: 282]

    public RatingServiceImpl(RatingResultRepository ratingResultRepository,
                             FacilityScoreRepository facilityScoreRepository,
                             PropertyRepository propertyRepository,
                             RatingLogService ratingLogService) {
        this.ratingResultRepository = ratingResultRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.ratingLogService = ratingLogService;
    }

    @Override
    @Transactional
    public RatingResult generateRating(Long propertyId) {
        // Ensure property exists 
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        // Ensure facility scores exist 
        FacilityScore scores = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new IllegalArgumentException("Cannot generate rating: No facility scores found for this property"));

        // Compute finalRating (Simple average of the 4 score fields) [cite: 285]
        double finalRating = (scores.getSchoolProximity() + 
                              scores.getHospitalProximity() + 
                              scores.getTransportAccess() + 
                              scores.getSafetyScore()) / 4.0;

        // Derive category based on thresholds [cite: 110, 286]
        String category = deriveCategory(finalRating);

        // Create and persist result [cite: 287]
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        result.setRatingCategory(category);
        // ratedAt is handled by @PrePersist in the entity [cite: 109]

        RatingResult savedResult = ratingResultRepository.save(result);

        // Optional logging [cite: 282]
        ratingLogService.addLog(propertyId, "Generated " + category + " rating with score: " + finalRating);

        return savedResult;
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        
        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("No rating result found for property id: " + propertyId)); // 
    }

    private String deriveCategory(double score) {
        if (score >= 8.5) return "EXCELLENT"; // [cite: 101]
        if (score >= 6.5) return "GOOD"; // [cite: 101]
        if (score >= 4.0) return "AVERAGE"; // [cite: 101]
        return "POOR"; // [cite: 101]
    }
}