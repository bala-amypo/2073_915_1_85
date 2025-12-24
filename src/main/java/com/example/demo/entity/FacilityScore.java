package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "facility_scores")
public class FacilityScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 80]

    @OneToOne
    @JoinColumn(name = "property_id", unique = true)
    private Property property; // [cite: 81, 93]

    @Min(0) @Max(10)
    private Integer schoolProximity; // [cite: 82, 92]

    @Min(0) @Max(10)
    private Integer hospitalProximity; // [cite: 83, 92]

    @Min(0) @Max(10)
    private Integer transportAccess; // [cite: 84, 92]

    @Min(0) @Max(10)
    private Integer safetyScore; // [cite: 85, 92]

    public FacilityScore() {} // [cite: 87]

    public FacilityScore(Property property, Integer schoolProximity, Integer hospitalProximity, 
                         Integer transportAccess, Integer safetyScore) {
        this.property = property;
        this.schoolProximity = schoolProximity;
        this.hospitalProximity = hospitalProximity;
        this.transportAccess = transportAccess;
        this.safetyScore = safetyScore;
    } // [cite: 88]

    // Getters and Setters...
    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
    public Integer getSchoolProximity() { return schoolProximity; }
    public Integer getHospitalProximity() { return hospitalProximity; }
    public Integer getTransportAccess() { return transportAccess; }
    public Integer getSafetyScore() { return safetyScore; }
}