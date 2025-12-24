package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FacilityScore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne @JoinColumn(name = "property_id")
    private Property property;
    private Integer schoolProximity;
    private Integer hospitalProximity;
    private Integer transportAccess;
    private Integer safetyScore;

    public Long getId() { return id; }
    public Property getProperty() { return property; }
    public void setProperty(Property p) { this.property = p; }
    public void setSchoolProximity(Integer v) { this.schoolProximity = v; }
    public void setHospitalProximity(Integer v) { this.hospitalProximity = v; }
    public void setTransportAccess(Integer v) { this.transportAccess = v; }
    public void setSafetyScore(Integer v) { this.safetyScore = v; }
}