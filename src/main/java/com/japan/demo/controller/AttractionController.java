package com.japan.demo.controller;

import com.japan.demo.mapper.AttractionMapper;
import com.japan.demo.model.Attraction;
import com.japan.demo.model.dto.AttractionDto;
import com.japan.demo.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;
    private final AttractionMapper attractionMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public AttractionDto saveAttraction(@RequestBody AttractionDto attractionDto) {
        return attractionMapper.attractionToAttractionDto(attractionService.save(attractionMapper.attractionDtoToAttraction(attractionDto), attractionDto.getCityId()));
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public AttractionDto updateAttraction(@RequestBody AttractionDto attractionDto) {
        return attractionMapper.attractionToAttractionDto(attractionService.update(attractionMapper.attractionDtoToAttraction(attractionDto), attractionDto.getCityId()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteAttraction(@PathVariable Long id) {
        attractionService.delete(id);
    }

    @GetMapping
    public Page<AttractionDto> getAttractionPage(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Attraction> attractionPage = attractionService.getPage(pageable);
        return attractionPage.map(attractionMapper::attractionToAttractionDto);
    }

    @GetMapping("/{id}")
    public AttractionDto getAttractionById(@PathVariable Long id) {
        return attractionMapper.attractionToAttractionDto(attractionService.findById(id));
    }
}
