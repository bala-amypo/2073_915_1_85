package com.example.demo.service;

import com.example.demo.entity.RatingResult;

public interface RatingService {
    /**
     * Calculates and persists the final rating based on FacilityScores.
     */
    RatingResult generateRating(Long propertyId);

    /**
     * Retrieves the already generated rating for a property.
     */
    RatingResult getRating(Long propertyId);
}