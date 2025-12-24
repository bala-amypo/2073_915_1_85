package com.example.demo.service;

import com.example.demo.entity.FacilityScore;

public interface FacilityScoreService {
    FacilityScore addScore(Long propertyId, FacilityScore score); // [cite: 264]
    FacilityScore getScoreByProperty(Long propertyId); // [cite: 265]
}