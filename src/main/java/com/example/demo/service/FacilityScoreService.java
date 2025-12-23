package com.example.demo.service;

import com.example.demo.entity.FacilityScore;

public interface FacilityScoreService {
    /**
     * Submits scores for a specific property.
     */
    FacilityScore addScore(Long propertyId, FacilityScore score);

    /**
     * Fetches the scores associated with a property.
     */
    FacilityScore getScoreByProperty(Long propertyId);
}