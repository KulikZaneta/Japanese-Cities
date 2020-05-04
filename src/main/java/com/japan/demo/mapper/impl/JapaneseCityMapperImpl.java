package com.japan.demo.mapper.impl;

import com.japan.demo.mapper.AttractionMapper;
import com.japan.demo.mapper.JapaneseCityMapper;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.model.dto.JapaneseCityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JapaneseCityMapperImpl implements JapaneseCityMapper {
    private final AttractionMapper attractionMapper;

    @Override
    public JapaneseCity japaneseCityDtoToJapaneseCity(JapaneseCityDto japaneseCityDto) {
        return JapaneseCity.builder()
                .area(japaneseCityDto.getArea())
                .description(japaneseCityDto.getDescription())
                .id(japaneseCityDto.getId())
                .name(japaneseCityDto.getName())
                .population(japaneseCityDto.getPopulation())
                .build();
    }

    @Override
    public JapaneseCityDto japaneseCityToJapaneseCityDto(JapaneseCity japaneseCity) {
        return JapaneseCityDto.builder()
                .area(japaneseCity.getArea())
                .description(japaneseCity.getDescription())
                .id((japaneseCity.getId()))
                .name(japaneseCity.getName())
                .population(japaneseCity.getPopulation())
                .attractions(attractionMapper.attractionListToAttractionListDto(japaneseCity.getAttractions()))
                .build();
    }

    @Override
    public List<JapaneseCityDto> japaneseCityListToJapaneseCityDtoList(List<JapaneseCity> japaneseCity) {
        return japaneseCity.stream()
                .map(this::japaneseCityToJapaneseCityDto)
                .collect(Collectors.toList());
    }
}
