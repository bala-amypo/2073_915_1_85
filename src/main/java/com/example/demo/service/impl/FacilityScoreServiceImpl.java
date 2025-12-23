package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {
    private final FacilityScoreRepository scoreRepo;
    private final PropertyRepository propRepo;
    private final RatingLogService logService;

    public FacilityScoreServiceImpl(FacilityScoreRepository scoreRepo, 
                                   PropertyRepository propRepo, 
                                   RatingLogService logService) {
        this.scoreRepo = scoreRepo;
        this.propRepo = propRepo;
        this.logService = logService;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        score.setProperty(property);
        FacilityScore saved = scoreRepo.save(score);
        
        // Log the scoring stage
        logService.addLog(propertyId, "Facility scores submitted for property: " + property.getTitle());
        return saved;
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        return scoreRepo.findByPropertyId(propertyId).orElse(null);
    }
}