package com.japan.demo.mapper;

import com.japan.demo.mapper.impl.AttractionMapperImp;
import com.japan.demo.model.Attraction;
import com.japan.demo.model.dto.AttractionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionMapperTest {
    @Autowired
    private AttractionMapperImp attractionMapper;

    @Test
    public void mapToAttractionTest() {
        //given
        AttractionDto attractionDto = new AttractionDto(1L, "test name", "test url", new ArrayList<>());

        //when
        Attraction attraction = attractionMapper.attractionDtoToAttraction(attractionDto);

        //then
        assertEquals(attractionDto.getId(), attraction.getId());
        assertEquals(attractionDto.getName(), attraction.getName());
        assertEquals(attractionDto.getUrl(), attraction.getUrl());
    }

    @Test
    public void mapToAttractionDtoTest() {
        //given
        Attraction attraction = new Attraction(1L, "test name", "test url", new HashSet<>());

        //when
        AttractionDto attractionDto = attractionMapper.attractionToAttractionDto(attraction);

        //then
        assertEquals(attraction.getId(), attractionDto.getId());
        assertEquals(attraction.getName(), attractionDto.getName());
        assertEquals(attraction.getUrl(), attractionDto.getUrl());
    }

    @Test
    public void mapToAttractionListTest() {
        //given
        List<AttractionDto> attractionDtoList = new ArrayList<>();
        attractionDtoList.add(new AttractionDto(1L, "test name", "test url", new ArrayList<>()));

        //when
       List<Attraction> attractionList = attractionMapper.attractionListDtoToAttractionList(attractionDtoList);

        //then
        assertEquals(attractionDtoList.get(0).getId(), attractionList.get(0).getId());
        assertEquals(attractionDtoList.get(0).getName(), attractionList.get(0).getName());
        assertEquals(attractionDtoList.get(0).getUrl(), attractionList.get(0).getUrl());
        assertEquals(attractionDtoList.size(), attractionList.size());
    }

    @Test
    public void mapToAttractionDtoListTest() {
        //given
        List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(new Attraction(1L, "test name", "test url", new HashSet<>()));

        //when
        List<AttractionDto> attractionDtoList = attractionMapper.attractionListToAttractionListDto(attractionList);

        //then
        assertEquals(attractionList.get(0).getId(), attractionDtoList.get(0).getId());
        assertEquals(attractionList.get(0).getName(), attractionDtoList.get(0).getName());
        assertEquals(attractionList.get(0).getUrl(), attractionDtoList.get(0).getUrl());
        assertEquals(attractionList.size(), attractionDtoList.size());
    }
}
