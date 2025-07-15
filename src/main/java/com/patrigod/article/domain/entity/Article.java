package com.patrigod.article.domain.entity;

import com.patrigod.city.domain.entity.City;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.rating.domain.entity.Rating;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Article {

    private Long id;

    private City city;

    private String name;

    private String description;

    private List<Rating> rating = new ArrayList<>();
}
