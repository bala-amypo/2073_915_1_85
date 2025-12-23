package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import java.util.List;

public interface RatingLogService {
    /**
     * Adds a log entry for a specific property event.
     */
    RatingLog addLog(Long propertyId, String message);

    /**
     * Retrieves all logs for a specific property.
     */
    List<RatingLog> getLogsByProperty(Long propertyId);
}