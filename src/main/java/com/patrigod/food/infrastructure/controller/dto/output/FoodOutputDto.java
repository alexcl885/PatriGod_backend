package com.patrigod.food.infrastructure.controller.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.domain.entity.City;

import com.patrigod.rating.infrastructure.controller.dto.output.RatingOutputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodOutputDto {

    private Long id;

    private City city;

    private String name;

    private List<RatingOutputDto> ratings = new ArrayList<>();

    private String image;

    private String type;

    private String origin;

    private String mainIngredients;

    private Integer approxCalories;

    private String consumptionMoment;

    private Boolean suitableForVegetarians;

    private String recommendedSideDishes;

    private String curiosities;

    private String description;
}
