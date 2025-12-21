package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
}