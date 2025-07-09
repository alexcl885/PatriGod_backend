package com.patrigod.monument.infrastructure.controller.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.infraestructure.controller.dto.output.CityOutputDto;
import com.patrigod.rating.infrastructure.repository.jpa.entity.RatingJpa;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonumentOutputDto {
    private Long id;

    private CityOutputDto city;

    private String name;

    private List<RatingJpa> ratings = new ArrayList<>();

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
