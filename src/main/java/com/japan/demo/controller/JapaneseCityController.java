package com.japan.demo.controller;

import com.japan.demo.mapper.JapaneseCityMapper;
import com.japan.demo.model.dto.JapaneseCityDto;
import com.japan.demo.service.JapaneseCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/city")
@RequiredArgsConstructor
public class JapaneseCityController {
    private final JapaneseCityMapper mapper;

    private final JapaneseCityService service;

    @GetMapping("/all")
    public List<JapaneseCityDto> allCities() {
        return mapper.japaneseCityListToJapaneseCityDtoList(service.findAll());
    }

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
    public JapaneseCityDto addCity(@RequestBody JapaneseCityDto japaneseCityDto) {
        return mapper.japaneseCityToJapaneseCityDto(service.save(mapper.japaneseCityDtoToJapaneseCity(japaneseCityDto)));
    }

    @PutMapping
    public JapaneseCityDto updateCity(@RequestBody JapaneseCityDto japaneseCityDto) {
        return mapper.japaneseCityToJapaneseCityDto(service.update(mapper.japaneseCityDtoToJapaneseCity(japaneseCityDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
            service.deleteById(id);
    }
}
