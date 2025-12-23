package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingLogService;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingResultRepository ratingRepo;
    private final FacilityScoreRepository scoreRepo;
    private final RatingLogService logService;

    public RatingServiceImpl(RatingResultRepository ratingRepo, 
                             FacilityScoreRepository scoreRepo, 
                             RatingLogService logService) {
        this.ratingRepo = ratingRepo;
        this.scoreRepo = scoreRepo;
        this.logService = logService;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        FacilityScore scores = scoreRepo.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("Scores not submitted"));

        // Calculate Average
        double avg = (scores.getSchoolProximity() + scores.getHospitalProximity() + 
                     scores.getTransportAccess() + scores.getSafetyScore()) / 4.0;

        RatingResult result = new RatingResult();
        result.setProperty(scores.getProperty());
        result.setFinalRating(avg);

        // Business Rules for Categories
        if (avg >= 8) result.setRatingCategory("EXCELLENT");
        else if (avg >= 6) result.setRatingCategory("GOOD");
        else if (avg >= 4) result.setRatingCategory("AVERAGE");
        else result.setRatingCategory("POOR");

        RatingResult savedResult = ratingRepo.save(result);
        
        // Log the rating generation stage
        logService.addLog(propertyId, "Rating generated: " + savedResult.getRatingCategory());
        
        return savedResult;
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        return ratingRepo.findByPropertyId(propertyId).orElse(null);
    }
}