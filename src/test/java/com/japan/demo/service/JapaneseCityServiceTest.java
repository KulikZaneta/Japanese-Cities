package com.japan.demo.service;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.AttractionRepository;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.impl.JapaneseCityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class JapaneseCityServiceTest {
    @InjectMocks
    private JapaneseCityServiceImpl japaneseCityService;

    @Mock
    private JapaneseCityRepository japaneseCityRepository;

    @Mock
    private AttractionRepository attractionRepository;

    @Test
    public void shouldFindByNameJapaneseCity() {
        //given
        JapaneseCity japaneseCity = new JapaneseCity(1L, "test name", "test population", "test area", "test desc", new ArrayList<>());
        when(japaneseCityRepository.findByName("test name")).thenReturn(japaneseCity);

        //when
        JapaneseCity res = japaneseCityService.findByName("test name");

        //then
        assertEquals(japaneseCity.getName(), res.getName());
    }

    @Test
    public void shouldFindByIdJapaneseCity() {
        //given
        JapaneseCity japaneseCity = new JapaneseCity(1L, "test name", "test population", "test area", "test desc", new ArrayList<>());
        when(japaneseCityRepository.findById(1L)).thenReturn(java.util.Optional.of(japaneseCity));

        //when
        JapaneseCity res = japaneseCityService.findById(1L);

        //then
        assertEquals(japaneseCity.getId(), res.getId());
    }

    @Test
    public void shouldSaveJapaneseCityWithAttraction() {
        when(japaneseCityRepository.save(any())).thenReturn(new JapaneseCity());

        JapaneseCity res = japaneseCityService.save(JapaneseCity.builder().id(1L).build(), new ArrayList<>());

        verify(japaneseCityRepository, times(1)).save(any());
        verify(attractionRepository, times(1)).saveAll(any());
        assertThat(res).isNotNull();
    }

    @Test
    public void shouldUpdateJapaneseCity() {
        when(japaneseCityRepository.save(any())).thenReturn(new JapaneseCity());

        JapaneseCity res = japaneseCityService.update(JapaneseCity.builder().id(1L).build(), new ArrayList<>());

        verify(japaneseCityRepository, times(1)).save(any());
        verify(attractionRepository, times(1)).saveAll(any());
        assertThat(res).isNotNull();
    }
}
