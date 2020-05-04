package com.japan.demo.service;


import com.japan.demo.model.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttractionService {
    Attraction save(Attraction attraction, Long cityId);

    Attraction update(Attraction attraction, Long cityId);

    void delete(Long id);

    Page<Attraction> getPage(Pageable pageable);

    Attraction findById(Long id);
}
