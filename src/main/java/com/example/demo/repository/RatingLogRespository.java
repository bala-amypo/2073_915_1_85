package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating_logs")
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Property property;

    private String message;
    private LocalDateTime loggedAt;

    public RatingLog() {}

    public RatingLog(Property property, String message, LocalDateTime loggedAt) {
        this.property = property;
        this.message = message;
        this.loggedAt = loggedAt;
    }

    @PrePersist
    public void onCreate() {
        loggedAt = LocalDateTime.now();
    }
}