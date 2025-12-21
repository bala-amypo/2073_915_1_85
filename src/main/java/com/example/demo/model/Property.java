package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;
    private String city;

    @DecimalMin(value = "0.1", message = "Price must be > 0") //
    private Double price;

    @DecimalMin(value = "100.0", message = "Area must be >= 100 sq ft") //
    private Double areaSqFt;
}