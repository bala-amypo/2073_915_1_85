package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating_results")
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Property property;

    private Double finalRating;
    private String ratingCategory;
    private LocalDateTime ratedAt;

    public RatingResult() {}

    public RatingResult(Property property, Double finalRating,
                        String ratingCategory, LocalDateTime ratedAt) {
        this.property = property;
        this.finalRating = finalRating;
        this.ratingCategory = ratingCategory;
        this.ratedAt = ratedAt;
    }

    @PrePersist
    public void onCreate() {
        if (ratedAt == null) {
            ratedAt = LocalDateTime.now();
        }
    }

    // getters
    public Double getFinalRating() { return finalRating; }
    public String getRatingCategory() { return ratingCategory; }
}
