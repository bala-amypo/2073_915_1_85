package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_scores")
public class FacilityScore {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne 
    @JoinColumn(name = "property_id")
    private Property property;

    private Integer schoolProximity;
    private Integer hospitalProximity;
    private Integer transportAccess;
    private Integer safetyScore;

    public FacilityScore() {}

    // Getters
    public Long getId() { return id; }
    public Property getProperty() { return property; }
    public Integer getSchoolProximity() { return schoolProximity; }
    public Integer getHospitalProximity() { return hospitalProximity; }
    public Integer getTransportAccess() { return transportAccess; }
    public Integer getSafetyScore() { return safetyScore; }

    // Setters
    public void setProperty(Property property) { this.property = property; }
    public void setSchoolProximity(Integer schoolProximity) { this.schoolProximity = schoolProximity; }
    public void setHospitalProximity(Integer hospitalProximity) { this.hospitalProximity = hospitalProximity; }
    public void setTransportAccess(Integer transportAccess) { this.transportAccess = transportAccess; }
    public void setSafetyScore(Integer safetyScore) { this.safetyScore = safetyScore; }
}