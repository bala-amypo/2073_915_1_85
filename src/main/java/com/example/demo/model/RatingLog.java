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

    @PrePersist
    void onCreate() {
        loggedAt = LocalDateTime.now();
    }

    public RatingLog() {}

    public RatingLog(Property property, String message) {
        this.property = property;
        this.message = message;
    }

    // getters & setters
}
