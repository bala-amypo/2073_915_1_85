package com.example.demo.repository;
import com.example.demo.model.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
}