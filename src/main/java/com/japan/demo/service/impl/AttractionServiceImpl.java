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
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final JapaneseCityRepository japaneseCityRepository;

    @Override
    @Transactional
    public Attraction save(Attraction attraction, List<Long> cityId) {
        Set<JapaneseCity> japaneseCities = japaneseCityRepository.findByIdIn(cityId);
        attraction.setJapaneseCity(japaneseCities);
        return attractionRepository.save(attraction);
    }

    @Override
    public Attraction update(Attraction attraction, List<Long> cityId) {
        if (attraction.getId() != null) {
            return save(attraction, cityId);
        }
        throw new EntityNotFoundException("Japanese city with " + cityId + "doesn't exist");
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
