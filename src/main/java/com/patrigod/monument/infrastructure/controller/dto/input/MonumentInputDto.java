package com.patrigod.monument.infrastructure.controller.dto.input;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.rating.infrastructure.repository.jpa.entity.RatingJpa;

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
public class MonumentInputDto {
    private Long id;

    private Long idCity;

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

    private String type;
}
