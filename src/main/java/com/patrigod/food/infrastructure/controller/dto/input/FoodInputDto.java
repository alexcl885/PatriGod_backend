package com.patrigod.food.infrastructure.controller.dto.input;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.rating.infrastructure.controller.dto.input.RatingInputDto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodInputDto {
    private Long id;

    private Long cityId;

    private String name;

    private List<RatingInputDto> ratings = new ArrayList<>();

    private String image;

    private String description;

    private String type;

    private String foodType;

    private String origin;

    private String mainIngredients;

    private Integer approxCalories;

    private String consumptionMoment;

    private Boolean suitableForVegetarians;

    private String recommendedSides;

    private String curiosities;
}
