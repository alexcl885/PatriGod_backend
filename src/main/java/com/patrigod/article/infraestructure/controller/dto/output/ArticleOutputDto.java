package com.patrigod.article.infraestructure.controller.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.rating.infrastructure.controller.dto.output.RatingOutputDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleOutputDto {
    private Long id;

    private CityJpa city;

    private String name;

    private String description;

    private List<RatingOutputDto> rating = new ArrayList<>();

}
