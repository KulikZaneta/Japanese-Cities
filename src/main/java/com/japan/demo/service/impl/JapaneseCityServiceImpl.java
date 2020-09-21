package com.japan.demo.service.impl;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.AttractionRepository;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.JapaneseCityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JapaneseCityServiceImpl implements JapaneseCityService {

    private final JapaneseCityRepository japaneseCityRepository;
    private final AttractionRepository attractionRepository;

    @Override
    @Cacheable(cacheNames = "mapCity", key = "#name")
    public JapaneseCity findByName(String name) {
        return japaneseCityRepository.findByName(name);
    }

    @Override
    @Cacheable(cacheNames = "mapCity", key = "#id")
    public JapaneseCity findById(Long id) {
        return japaneseCityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City with " + id + "doesn't exist"));
    }

    @Transactional
    @Override
    @CachePut(cacheNames = "mapCity", key = "#result.id")
    public JapaneseCity save(JapaneseCity japaneseCities, List<Attraction> attractions) {
        attractions.forEach(attraction ->
                attraction.setJapaneseCity(Collections.singleton(japaneseCities)));
        japaneseCities.setAttractions(attractions);
        attractionRepository.saveAll(attractions);
        return japaneseCityRepository.save(japaneseCities);
    }

    @Transactional
    @Override
    @CachePut(cacheNames = "mapCity", key = "#result.id")
    public JapaneseCity update(JapaneseCity japaneseCity, List<Attraction> attractions) {
        if (japaneseCity != null) {
            JapaneseCity byId = this.findById(japaneseCity.getId());
            byId.setArea(japaneseCity.getArea());
            byId.setDescription(japaneseCity.getDescription());
            byId.setName(japaneseCity.getName());
            byId.setPopulation(japaneseCity.getPopulation());
            byId.getAttractions().addAll(attractions);

            attractions.forEach(attraction -> attraction.setJapaneseCity(Collections.singleton(japaneseCity)));

            return japaneseCityRepository.save(byId);
        }
        throw new EntityNotFoundException("City doesn't exist");
    }

    @Override
    @CacheEvict(cacheNames = "mapCity", key = "#id")
    public void deleteById(Long id) {
        japaneseCityRepository.deleteById(id);
    }

    @Override
    public List<String> autoCompleteByName(String name) {
        return japaneseCityRepository.autoCompleteByName(name);
    }

    @Override
    public List<JapaneseCityRepository.JapaneseCitySelect> getIdAndName() {
        return japaneseCityRepository.getIdAndName();
    }

    @Override
    public Page<JapaneseCity> getPage(Pageable pageable) {
        return japaneseCityRepository.findAll(pageable);
    }
}
