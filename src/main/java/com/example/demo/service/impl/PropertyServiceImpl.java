package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository; // [cite: 256]

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property addProperty(Property property) {
        if (property.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0"); // [cite: 258]
        }
        if (property.getAreaSqFt() < 100) {
            throw new IllegalArgumentException("Area must be at least 100 sq ft"); // [cite: 258]
        }
        return propertyRepository.save(property); // [cite: 260]
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll(); // [cite: 261]
    }
}