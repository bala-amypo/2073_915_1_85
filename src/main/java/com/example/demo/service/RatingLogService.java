package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import java.util.List;

public interface RatingLogService {
    RatingLog addLog(Long propertyId, String message); // [cite: 291]
    List<RatingLog> getLogsByProperty(Long propertyId); // [cite: 292]
}