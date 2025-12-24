package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating_results")
public class RatingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 98]

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property; // [cite: 99, 107]

    private Double finalRating; // [cite: 100]
    private String ratingCategory; // [cite: 101]
    private LocalDateTime ratedAt; // [cite: 102]

    @PrePersist
    protected void onCreate() {
        this.ratedAt = LocalDateTime.now(); // [cite: 109]
    }

    public RatingResult() {} // [cite: 104]

    public RatingResult(Property property, Double finalRating, String ratingCategory, LocalDateTime ratedAt) {
        this.property = property;
        this.finalRating = finalRating;
        this.ratingCategory = ratingCategory;
        this.ratedAt = ratedAt;
    } // [cite: 105]

    // Getters and Setters...
    public void setProperty(Property property) { this.property = property; }
    public void setFinalRating(Double finalRating) { this.finalRating = finalRating; }
    public void setRatingCategory(String ratingCategory) { this.ratingCategory = ratingCategory; }
}