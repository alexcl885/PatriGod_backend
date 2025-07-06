package com.patrigod.monument.domain.entity;

import com.patrigod.article.domain.entity.Article;
import com.patrigod.rating.infrastructure.repository.jpa.entity.RatingJpa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Monument extends Article {

    private Long id;

    private Long idCiudad;

    private String name;

    private List<RatingJpa> ratings = new ArrayList<>();

    private String description;

    private String image;

    private String architecturalStyle;

    private String constructionPeriod;

    private String location;

    private String visitingHours;

    private String entranceFee;

    private String unescoDeclaration;

    private Double height;

    private String mainMaterials;

    private String curiosities;

}
