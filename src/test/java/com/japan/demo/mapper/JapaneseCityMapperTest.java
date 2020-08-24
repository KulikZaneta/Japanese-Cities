package com.japan.demo.mapper;

import com.japan.demo.mapper.impl.JapaneseCityMapperImpl;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.model.dto.JapaneseCityDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JapaneseCityMapperTest {
    @Autowired
    private JapaneseCityMapperImpl japaneseCityMapper;

    @Test
    public void mapToJapaneseCityTest() {
        //given
        JapaneseCityDto japaneseCityDto = new JapaneseCityDto(1L, "test name", "test population", "test area", "test desc", new ArrayList<>());

        //when
        JapaneseCity japaneseCity = japaneseCityMapper.japaneseCityDtoToJapaneseCity(japaneseCityDto);

        //then
        assertEquals(japaneseCityDto.getId(), japaneseCityDto.getId());
        assertEquals(japaneseCityDto.getName(), japaneseCityDto.getName());
        assertEquals(japaneseCityDto.getPopulation(), japaneseCityDto.getPopulation());
        assertEquals(japaneseCityDto.getArea(), japaneseCityDto.getArea());
        assertEquals(japaneseCityDto.getAttractions(), japaneseCityDto.getAttractions());
    }

    @Test
    public void mapToJapaneseDtoTest() {
        //given
        JapaneseCity japaneseCity = new JapaneseCity(1L, "test name", "test population", "test area", "test desc", new ArrayList<>());

        //when
        JapaneseCityDto japaneseCityDto = japaneseCityMapper.japaneseCityToJapaneseCityDto(japaneseCity);

        //then
        assertEquals(japaneseCityDto.getId(), japaneseCityDto.getId());
        assertEquals(japaneseCityDto.getName(), japaneseCityDto.getName());
        assertEquals(japaneseCityDto.getPopulation(), japaneseCityDto.getPopulation());
        assertEquals(japaneseCityDto.getArea(), japaneseCityDto.getArea());
        assertEquals(japaneseCityDto.getAttractions(), japaneseCityDto.getAttractions());
    }

    @Test
    public void mapToJapaneseCityDtoListTest() {
        //given
        List<JapaneseCity> japaneseCities = new ArrayList<>();
        japaneseCities.add(new JapaneseCity(1L, "test name", "test population", "test area", "test desc", new ArrayList<>()));

        //when
        List<JapaneseCityDto> japaneseCityDtoList = japaneseCityMapper.japaneseCityListToJapaneseCityDtoList(japaneseCities);

        //then
        assertEquals(japaneseCities.get(0).getId(), japaneseCityDtoList.get(0).getId());
        assertEquals(japaneseCities.get(0).getName(), japaneseCityDtoList.get(0).getName());
        assertEquals(japaneseCities.get(0).getPopulation(), japaneseCityDtoList.get(0).getPopulation());
        assertEquals(japaneseCities.get(0).getArea(), japaneseCityDtoList.get(0).getArea());
        assertEquals(japaneseCities.get(0).getAttractions(), japaneseCityDtoList.get(0).getAttractions());
        assertEquals(japaneseCities.size(), japaneseCityDtoList.size());
    }
}
