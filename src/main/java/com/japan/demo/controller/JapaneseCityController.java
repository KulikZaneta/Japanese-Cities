package com.japan.demo.controller;

import com.japan.demo.mapper.AttractionMapper;
import com.japan.demo.mapper.JapaneseCityMapper;
import com.japan.demo.model.JapaneseCity;
import com.japan.demo.model.dto.JapaneseCityDto;
import com.japan.demo.repository.JapaneseCityRepository;
import com.japan.demo.service.JapaneseCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/city")
@RequiredArgsConstructor
public class JapaneseCityController {

    private final JapaneseCityMapper mapper;
    private final JapaneseCityService service;
    private final AttractionMapper attractionMapper;

    @GetMapping("/{id}")
    public JapaneseCityDto cityById(@PathVariable Long id) {
        return mapper.japaneseCityToJapaneseCityDto(service.findById(id));
    }

    @GetMapping
    public JapaneseCityDto nameCity(@RequestParam String name) {
        return mapper.japaneseCityToJapaneseCityDto(service.findByName(name));
    }

    @GetMapping("/auto-complete")
    public List<String> autoCompleteByName(@RequestParam String name) {
        return service.autoCompleteByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public JapaneseCityDto addCity(@RequestBody JapaneseCityDto japaneseCityDto) {
        return mapper.japaneseCityToJapaneseCityDto(service.save(mapper.japaneseCityDtoToJapaneseCity(japaneseCityDto), attractionMapper.attractionListDtoToAttractionList(japaneseCityDto.getAttractions())));
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public JapaneseCityDto updateCity(@RequestBody JapaneseCityDto japaneseCityDto) {
        return mapper.japaneseCityToJapaneseCityDto(service.update(mapper.japaneseCityDtoToJapaneseCity(japaneseCityDto), attractionMapper.attractionListDtoToAttractionList(japaneseCityDto.getAttractions())));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void deleteCity(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/select")
    public List<JapaneseCityRepository.JapaneseCitySelect> getIdAndName() {
        return service.getIdAndName();
    }

    @GetMapping("/page")
    public Page<JapaneseCityDto> getJapaneseCityPage(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JapaneseCity> japaneseCityPage = service.getPage(pageable);
        return japaneseCityPage.map(mapper::japaneseCityToJapaneseCityDto);
    }
}
