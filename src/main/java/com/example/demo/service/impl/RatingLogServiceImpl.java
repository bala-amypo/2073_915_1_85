package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingLog;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {
    private final RatingLogRepository logRepo;
    private final PropertyRepository propRepo;

    public RatingLogServiceImpl(RatingLogRepository logRepo, PropertyRepository propRepo) {
        this.logRepo = logRepo;
        this.propRepo = propRepo;
    }

    @Override
    public RatingLog addLog(Long propertyId, String message) {
        Property property = propRepo.findById(propertyId).orElse(null);
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);
        return logRepo.save(log);
    }

    @Override
    public List<RatingLog> getLogsByProperty(Long propertyId) {
        return logRepo.findByPropertyId(propertyId);
    }
}