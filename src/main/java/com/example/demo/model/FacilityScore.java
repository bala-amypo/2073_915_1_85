package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facility_scores")
public class FacilityScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Min(0) @Max(10)
    private Integer schoolProximity;

    @Min(0) @Max(10)
    private Integer hospitalProximity;

    @Min(0) @Max(10)
    private Integer transportAccess;

    @Min(0) @Max(10)
    private Integer safetyScore;

    private LocalDateTime submittedAt;

    @PrePersist
    protected void onCreate() { submittedAt = LocalDateTime.now(); }

    // Getters and Setters omitted for brevity
    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
    public Integer getSchoolProximity() { return schoolProximity; }
    public void setSchoolProximity(Integer schoolProximity) { this.schoolProximity = schoolProximity; }
    public Integer getHospitalProximity() { return hospitalProximity; }
    public void setHospitalProximity(Integer hospitalProximity) { this.hospitalProximity = hospitalProximity; }
    public Integer getTransportAccess() { return transportAccess; }
    public void setTransportAccess(Integer transportAccess) { this.transportAccess = transportAccess; }
    public Integer getSafetyScore() { return safetyScore; }
    public void setSafetyScore(Integer safetyScore) { this.safetyScore = safetyScore; }
}