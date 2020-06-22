package com.japan.demo.repository;

import com.japan.demo.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
