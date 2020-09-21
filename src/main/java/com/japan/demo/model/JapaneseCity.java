package com.japan.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JapaneseCity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String population;

    private String area;

    @Column(length = 10_000)
    private String description;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "japaneseCity", cascade = CascadeType.ALL)
    private List<Attraction> attractions;
}
