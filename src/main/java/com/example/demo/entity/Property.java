package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<RatingLog> ratingLogs = new ArrayList<>();

    @ManyToMany(mappedBy = "assignedProperties")
    private List<User> assignedUsers = new ArrayList<>();

    public Property() {}
    public Long getId() { return id; }
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
    public List<User> getAssignedUsers() { return assignedUsers; }
    public void addRatingLog(RatingLog log) { this.ratingLogs.add(log); log.setProperty(this); }
}