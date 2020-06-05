package com.japan.demo.service;

import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.JapaneseCityRepository;

import java.util.List;

public interface JapaneseCityService {
     JapaneseCity findByName(String name);

     JapaneseCity findById(Long id);

     List<JapaneseCity> findAll();

     JapaneseCity save(JapaneseCity japaneseCities);

     JapaneseCity update(JapaneseCity japaneseCity);

     void deleteById(Long id);

     List<String> autoCompleteByName(String name);

     List<JapaneseCityRepository.JapaneseCitySelect> getIdAndName();
}
