package com.patrigod.food.domain.entity;

import com.patrigod.article.domain.entity.Article;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Food extends Article {

    private String image;

    private String typeOfFood;

    private String origin;

    private String mainIngredients;

    private Integer approxCalories;

    private String consumptionMoment;

    private Boolean suitableForVegetarians;

    private String recommendedSideDishes;

    private String curiosities;

    private String description;

    private String type;
    
}
