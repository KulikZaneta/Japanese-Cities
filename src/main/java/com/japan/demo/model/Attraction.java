package com.japan.demo.model;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private JapaneseCity japaneseCity;


}
