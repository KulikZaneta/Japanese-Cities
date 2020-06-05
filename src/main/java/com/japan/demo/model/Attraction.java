package com.japan.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Attraction {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String url;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<JapaneseCity> japaneseCity;


}
