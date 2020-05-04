package com.japan.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JapaneseCityDto {
    private Long id;

    private String name;

    private String population;

    private String area;

    private String description;

    private List<AttractionDto> attractions;
}
