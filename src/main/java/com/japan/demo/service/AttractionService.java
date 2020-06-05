package com.japan.demo.service;


import com.japan.demo.model.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionService {
    Attraction save(Attraction attraction, List<Long> cityId);

    Attraction update(Attraction attraction, List<Long> cityId);

    void delete(Long id);

    Page<Attraction> getPage(Pageable pageable);

    Attraction findById(Long id);
}
