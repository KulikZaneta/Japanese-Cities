package com.japan.demo.mapper.impl;

import com.japan.demo.mapper.AttractionMapper;
import com.japan.demo.model.Attraction;
import com.japan.demo.model.dto.AttractionDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttractionMapperImp implements AttractionMapper {
    @Override
    public Attraction attractionDtoToAttraction(AttractionDto attractionDto) {
        return Attraction.builder()
                .id(attractionDto.getId())
                .name(attractionDto.getName())
                .url(attractionDto.getUrl())
                .build();
    }

    @Override
    public AttractionDto attractionToAttractionDto(Attraction attraction) {
        return AttractionDto.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .url(attraction.getUrl())
                .build();
    }

    @Override
    public List<Attraction> attractionListDtoToAttractionList(List<AttractionDto> attractionDto) {
        if (attractionDto != null) {
            return attractionDto.stream()
                    .map(this::attractionDtoToAttraction)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<AttractionDto> attractionListToAttractionListDto(List<Attraction> attractions) {
        if (attractions != null) {
            return attractions.stream()
                    .map(this::attractionToAttractionDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
