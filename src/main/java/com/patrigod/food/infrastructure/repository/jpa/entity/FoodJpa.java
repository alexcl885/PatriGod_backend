package com.patrigod.food.infrastructure.repository.jpa.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@NoArgsConstructor

@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("food")
@Table(name = "food")
public class FoodJpa extends ArticleJpa {

    @Column(length = 255)
    private String image;

    @Column(name = "typeOfFood",length = 100)
    private String typeOfFood;

    @Column(length = 100)
    private String origin;

    @Column(length = 255)
    private String mainIngredients;

    private Integer approxCalories;

    @Column(length = 255)
    private String consumptionMoment;

    private Boolean suitableForVegetarians;

    @Column(length = 255)
    private String recommendedSideDishes;

    @Column(columnDefinition = "TEXT")
    private String curiosities;
}
