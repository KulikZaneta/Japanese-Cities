package com.japan.demo.service.impl;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.AttractionRepository;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final JapaneseCityRepository japaneseCityRepository;

    @Override
    public Attraction save(Attraction attraction, Long cityId) {
        Optional<JapaneseCity> optionalCity = japaneseCityRepository.findById(cityId);
        JapaneseCity japaneseCity = optionalCity.orElseThrow(() -> new EntityNotFoundException("Japanese city with " + cityId + "doesn't exist"));
        attraction.setJapaneseCity(japaneseCity);
        return attractionRepository.save(attraction);
    }

    @Override
    public Attraction update(Attraction attraction, Long cityId) {
        if (attraction.getId() != null) {
             return save(attraction, cityId);
        } throw new  EntityNotFoundException("Japanese city with " + cityId + "doesn't exist");
    }

    @Override
    public void delete(Long id) {
        attractionRepository.deleteById(id);

    }

    @Override
    public Page<Attraction> getPage(Pageable pageable) {
        return attractionRepository.findAll(pageable);
    }

    @Override
    public Attraction findById(Long id) {
        return attractionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with" + id + "doesn't exist"));
    }
}
