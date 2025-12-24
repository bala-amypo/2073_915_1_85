package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 58]

    private String title; // [cite: 59]
    private String address; // [cite: 60]
    private String city; // [cite: 61]
    private Double price; // [cite: 62]
    private Double areaSqFt; // [cite: 63]

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private RatingResult ratingResult; // [cite: 68]

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs = new ArrayList<>(); // [cite: 69, 75, 126]

    @ManyToMany(mappedBy = "assignedProperties")
    private List<User> assignedUsers = new ArrayList<>(); // [cite: 71]

    public Property() {} // [cite: 65]

    public Property(String title, String address, String city, Double price, Double areaSqFt) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.price = price;
        this.areaSqFt = areaSqFt;
    } // [cite: 66]

    // Getters and Setters...
    public Long getId() { return id; }
    public Double getPrice() { return price; }
    public Double getAreaSqFt() { return areaSqFt; }
}