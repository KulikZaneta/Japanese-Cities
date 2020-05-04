package com.japan.demo.mapper;

import com.japan.demo.model.Attraction;
import com.japan.demo.model.dto.AttractionDto;

import java.util.List;

public interface AttractionMapper {
    Attraction attractionDtoToAttraction(AttractionDto attractionDto);

    AttractionDto attractionToAttractionDto(Attraction attraction);

    List<Attraction> attractionListDtoToAttractionList(List<AttractionDto> attractionDtos);

    List<AttractionDto> attractionListToAttractionListDto(List<Attraction> attractions);
}
