package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "properties")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String address;
    private String city;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;
    
    @NotNull
    @DecimalMin(value = "100.0", inclusive = true)
    private Double areaSqFt;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private RatingResult ratingResult;
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private FacilityScore facilityScore;
    
    @ManyToMany
    @JoinTable(
        name = "user_property_assignments",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assignedUsers = new HashSet<>();
    
    public Property() {}
    
    public Property(String title, String address, String city, Double price, Double areaSqFt) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.price = price;
        this.areaSqFt = areaSqFt;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Double getAreaSqFt() { return areaSqFt; }
    public void setAreaSqFt(Double areaSqFt) { this.areaSqFt = areaSqFt; }
    
    public RatingResult getRatingResult() { return ratingResult; }
    public void setRatingResult(RatingResult ratingResult) { this.ratingResult = ratingResult; }
    
    public List<RatingLog> getRatingLogs() { return ratingLogs; }
    public void setRatingLogs(List<RatingLog> ratingLogs) { this.ratingLogs = ratingLogs; }
    
    public FacilityScore getFacilityScore() { return facilityScore; }
    public void setFacilityScore(FacilityScore facilityScore) { this.facilityScore = facilityScore; }
    
    public Set<User> getAssignedUsers() { return assignedUsers; }
    public void setAssignedUsers(Set<User> assignedUsers) { this.assignedUsers = assignedUsers; }
    
    public void addRatingLog(RatingLog ratingLog) {
        if (this.ratingLogs == null) {
            this.ratingLogs = new java.util.ArrayList<>();
        }
        this.ratingLogs.add(ratingLog);
        ratingLog.setProperty(this);
    }
}