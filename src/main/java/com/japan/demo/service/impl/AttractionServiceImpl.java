package com.japan.demo.service.impl;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.AttractionRepository;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value = "mapAttractions", key = "#result.id")
    public Attraction save(Attraction attraction, List<Long> cityId) {
        Set<JapaneseCity> japaneseCities = japaneseCityRepository.findByIdIn(cityId);
        attraction.setJapaneseCity(japaneseCities);
        return attractionRepository.save(attraction);
    }

    @Transactional
    @Override
    @CachePut(value = "mapAttractions", key = "#result.id")
    public Attraction update(Attraction attraction, List<Long> cityId) {
        if (attraction.getId() != null) {
            return save(attraction, cityId);
        }
        throw new EntityNotFoundException("Attraction doesn't exist");
    }

    @Override
    @CacheEvict(value = "mapAttractions", key = "#result.id")
    public void delete(Long id) {
        attractionRepository.deleteById(id);
    }

    @Override
    public Page<Attraction> getPage(Pageable pageable) {
        return attractionRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "mapAttractions", key = "#id")
    public Attraction findById(Long id) {
        return attractionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with" + id + "doesn't exist"));
    }
}
