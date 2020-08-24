package com.japan.demo.service;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.repository.AttractionRepository;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.impl.AttractionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttractionServiceTest {

    @InjectMocks
    private AttractionServiceImpl attractionService;

    @Mock
    private JapaneseCityRepository japaneseCityRepository;

    @Mock
    private AttractionRepository attractionRepository;

    @Test
    public void shouldSaveAttractionWithoutJapaneseCity() {
        when(japaneseCityRepository.findByIdIn(Arrays.asList(1L, 2L))).thenReturn(Collections.emptySet());
        when(attractionRepository.save(any())).thenReturn(new Attraction());

        Attraction result = attractionService.save(new Attraction(), Arrays.asList(1L, 2L));

        verify(attractionRepository, times(1)).save(any());
        verify(japaneseCityRepository, times(1)).findByIdIn(Arrays.asList(1L, 2L));
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldSaveAttractionWithJapaneseCity() {
        Set<JapaneseCity> japaneseCity = new HashSet<>();
        japaneseCity.add(new JapaneseCity(1L, "test name", "test population", "test area", "test desc", Collections.emptyList()));
        japaneseCity.add(new JapaneseCity(2L, "test name", "test population", "test area", "test desc", Collections.emptyList()));

        when(japaneseCityRepository.findByIdIn(Arrays.asList(1L, 2L))).thenReturn(japaneseCity);
        when(attractionRepository.save(any())).thenReturn(new Attraction());

        Attraction result = attractionService.save(new Attraction(), Arrays.asList(1L, 2L));

        verify(attractionRepository, times(1)).save(any());
        verify(japaneseCityRepository, times(1)).findByIdIn(Arrays.asList(1L, 2L));
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldUpdateThrownException() {
        assertThatThrownBy(() -> attractionService.update(new Attraction(), Arrays.asList(1L, 3L)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Attraction doesn't exist");

    }

    @Test
    public void shouldUpdateAttraction() {
        when(attractionRepository.save(any())).thenReturn(new Attraction());

        Attraction res = attractionService.update(Attraction.builder().id(1L).build(), Arrays.asList(1L, 2L));

        verify(attractionRepository, times(1)).save(any());
        assertThat(res).isNotNull();
    }

    @Test
    public void shouldFindByIdAttraction() {
        Attraction attraction = new Attraction(1L, "test name", "test url", new HashSet<>());

        when(attractionRepository.findById(anyLong())).thenReturn(Optional.of(attraction));

        Attraction res = attractionService.findById(1L);

        assertThat(res).isNotNull();
    }
}
