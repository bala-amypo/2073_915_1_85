package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class RatingLogController {
    private final RatingLogService logService;

    public RatingLogController(RatingLogService logService) {
        this.logService = logService;
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<List<RatingLog>> getLogs(@PathVariable Long propertyId) {
        return ResponseEntity.ok(logService.getLogsByProperty(propertyId));
    }
}