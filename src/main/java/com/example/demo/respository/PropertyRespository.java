package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByCity(String city);
}

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    Optional<FacilityScore> findByProperty(Property property);
}

public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
    Optional<RatingResult> findByProperty(Property property);
}

public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
    List<RatingLog> findByProperty(Property property);
}
