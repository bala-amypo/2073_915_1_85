package com.example.demo.service;

import com.example.demo.repository.RatingResultRepository;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingResultRepository ratingRepo;
    private final FacilityScoreRepository scoreRepo;
    private final PropertyRepository propRepo;

    // STEP 0 Requirement: Constructor Injection
    public RatingService(RatingResultRepository ratingRepo, 
                         FacilityScoreRepository scoreRepo, 
                         PropertyRepository propRepo) {
        this.ratingRepo = ratingRepo;
        this.scoreRepo = scoreRepo;
        this.propRepo = propRepo;
    }
}