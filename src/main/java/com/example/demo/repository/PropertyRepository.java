package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    // Requirement: HQL implemented with @Query [cite: 176]
    @Query("SELECT p FROM Property p WHERE p.city = :city") 
    List<Property> findByCityHql(@Param("city") String city);

    // Requirement: Derived query [cite: 177]
    List<Property> findByCity(String city); 
}