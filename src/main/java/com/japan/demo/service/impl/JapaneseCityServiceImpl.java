package com.japan.demo.service.impl;

import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.JapaneseCityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JapaneseCityServiceImpl implements JapaneseCityService {

    private final JapaneseCityRepository repository;

    @Override
    public JapaneseCity findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public JapaneseCity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("City with " + id + "doesn't exist" ));
    }

    @Override
    public List<JapaneseCity> findAll() {
        return repository.findAll();
    }

    @Override
    public JapaneseCity save(JapaneseCity japaneseCities) {
        return repository.save(japaneseCities);
    }

    @Override
    public JapaneseCity update(JapaneseCity japaneseCity) {
        if (japaneseCity != null) {
            return save(japaneseCity);
        } throw new EntityNotFoundException("City doesn't exist" );
    }

    @Override
    public void deleteById(Long id) {
         repository.deleteById(id);
    }

    @Override
    public List<String> autoCompleteByName(String name) {
        return repository.autoCompleteByName(name);
    }
}
