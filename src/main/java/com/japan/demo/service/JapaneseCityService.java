package com.japan.demo.service;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.JapaneseCityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JapaneseCityService {
    JapaneseCity findByName(String name);

    JapaneseCity findById(Long id);

    JapaneseCity save(JapaneseCity japaneseCities, List<Attraction> attractions);

    JapaneseCity update(JapaneseCity japaneseCity, List<Attraction> attractions);

    void deleteById(Long id);

    List<String> autoCompleteByName(String name);

    List<JapaneseCityRepository.JapaneseCitySelect> getIdAndName();

    Page<JapaneseCity> getPage(Pageable pageable);
}
