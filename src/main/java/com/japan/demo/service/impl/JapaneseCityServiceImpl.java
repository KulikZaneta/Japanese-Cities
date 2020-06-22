package com.japan.demo.service.impl;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.AttractionRepository;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.JapaneseCityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public JapaneseCity findByName(String name) {
        return japaneseCityRepository.findByName(name);
    }

    @Override
    public JapaneseCity findById(Long id) {
        return japaneseCityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City with " + id + "doesn't exist"));
    }

    @Override
    public List<JapaneseCity> findAll() {
        return japaneseCityRepository.findAll();
    }

    @Transactional
    @Override
    public JapaneseCity save(JapaneseCity japaneseCities, List<Attraction> attractions) {
        attractions.forEach(attraction ->
                attraction.setJapaneseCity(Collections.singleton(japaneseCities)));
        japaneseCities.setAttractions(attractions);
        attractionRepository.saveAll(attractions);
        return japaneseCityRepository.save(japaneseCities);
    }

    @Override
    public JapaneseCity update(JapaneseCity japaneseCity, List<Attraction> attractions) {
        if (japaneseCity != null) {
            return save(japaneseCity, attractions);
        }
        throw new EntityNotFoundException("City doesn't exist");
    }

    @Override
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
