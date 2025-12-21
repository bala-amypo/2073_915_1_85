package com.example.demo.repository;
import com.example.demo.model.RatingLog; // or entity package
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
}