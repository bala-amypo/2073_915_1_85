package com.example.demo.service;

import com.example.demo.entity.Property;
import java.util.List;

public interface PropertyService {
    /**
     * Adds a new property to the database.
     */
    Property addProperty(Property property);

    /**
     * Retrieves all properties.
     */
    List<Property> getAllProperties();
}