package com.example.demo.service;

import com.example.demo.entity.Property;
import java.util.List;

public interface PropertyService {
    Property addProperty(Property property); // [cite: 253]
    List<Property> getAllProperties(); // [cite: 254]
}