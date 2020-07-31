package com.japan.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Attraction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String url;

    @ManyToMany(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<JapaneseCity> japaneseCity;
}
