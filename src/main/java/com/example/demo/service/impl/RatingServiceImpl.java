package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RatingServiceImpl implements RatingService {
    
    private final RatingResultRepository ratingResultRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    
    public RatingServiceImpl(RatingResultRepository ratingResultRepository, 
                           FacilityScoreRepository facilityScoreRepository,
                           PropertyRepository propertyRepository) {
        this.ratingResultRepository = ratingResultRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
    }
    
    @Override
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));
        
        FacilityScore facilityScore = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new BadRequestException("Property must have facility scores before rating can be generated"));
        
        // Calculate final rating (average of all scores)
        double finalRating = (facilityScore.getSchoolProximity() + 
                             facilityScore.getHospitalProximity() + 
                             facilityScore.getTransportAccess() + 
                             facilityScore.getSafetyScore()) / 4.0;
        
        // Determine rating category
        String ratingCategory = determineRatingCategory(finalRating);
        
        RatingResult ratingResult = new RatingResult(property, finalRating, ratingCategory, LocalDateTime.now());
        return ratingResultRepository.save(ratingResult);
    }
    
    @Override
    public RatingResult getRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));
        
        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for property id: " + propertyId));
    }
    
    private String determineRatingCategory(double rating) {
        if (rating >= 8.0) return "EXCELLENT";
        if (rating >= 6.0) return "GOOD";
        if (rating >= 4.0) return "AVERAGE";
        return "POOR";
    }
}