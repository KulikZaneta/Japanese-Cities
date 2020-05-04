package com.japan.demo.mapper;

import com.japan.demo.model.JapaneseCity;
import com.japan.demo.model.dto.JapaneseCityDto;

import java.util.List;

public interface JapaneseCityMapper {
    JapaneseCity japaneseCityDtoToJapaneseCity(JapaneseCityDto japaneseCityDto);

    JapaneseCityDto japaneseCityToJapaneseCityDto(JapaneseCity japaneseCity);

    List<JapaneseCityDto> japaneseCityListToJapaneseCityDtoList(List<JapaneseCity> japaneseCity);
}
