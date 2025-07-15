package com.patrigod.food.infrastructure.controller.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodInputDto {

    @NotNull(message = "ID cannot be null.")
    private Long id;

    @NotNull(message = "City ID cannot be null.")
    private Long cityId;

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 255, message = "Name must not exceed 255 characters.")
    private String name;

    @NotBlank(message = "Image URL cannot be blank.")
    @Size(max = 255, message = "Image URL must not exceed 255 characters.")
    private String image;

    @NotBlank(message = "Description cannot be blank.")
    @Size(max = 1000, message = "Description must not exceed 1000 characters.")
    private String description;

    @NotBlank(message = "Type cannot be blank.")
    @Size(max = 100, message = "Type must not exceed 100 characters.")
    private String type;

    @NotBlank(message = "Food type cannot be blank.")
    @Size(max = 100, message = "Food type must not exceed 100 characters.")
    private String foodType;

    @NotBlank(message = "Origin cannot be blank.")
    @Size(max = 100, message = "Origin must not exceed 100 characters.")
    private String origin;

    @NotBlank(message = "Main ingredients cannot be blank.")
    @Size(max = 255, message = "Main ingredients must not exceed 255 characters.")
    private String mainIngredients;

    @NotNull(message = "Approximate calories cannot be null.")
    @Positive(message = "Approximate calories must be a positive number.")
    private Integer approxCalories;

    @NotBlank(message = "Consumption moment cannot be blank.")
    @Size(max = 255, message = "Consumption moment must not exceed 255 characters.")
    private String consumptionMoment;

    @NotNull(message = "Suitable for vegetarians flag cannot be null.")
    private Boolean suitableForVegetarians;

    @NotBlank(message = "Recommended sides cannot be blank.")
    @Size(max = 255, message = "Recommended sides must not exceed 255 characters.")
    private String recommendedSides;

    @NotBlank(message = "Curiosities cannot be blank.")
    @Size(max = 2000, message = "Curiosities must not exceed 2000 characters.")
    private String curiosities;
}
