package com.japan.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class JapaneseCity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String population;

    private String area;

    private String description;

    @ManyToMany(mappedBy = "japaneseCity", cascade = CascadeType.ALL)
    private List<Attraction> attractions;
}