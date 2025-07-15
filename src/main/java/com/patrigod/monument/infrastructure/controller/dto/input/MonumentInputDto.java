package com.patrigod.monument.infrastructure.controller.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "City ID cannot be null.")
    private Long idCity;

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 255, message = "Name must not exceed 255 characters.")
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    @Size(max = 1000, message = "Description must not exceed 1000 characters.")
    private String description;

    @NotBlank(message = "Image URL cannot be blank.")
    @Size(max = 255, message = "Image URL must not exceed 255 characters.")
    private String image;

    @NotBlank(message = "Architectural style cannot be blank.")
    @Size(max = 100, message = "Architectural style must not exceed 100 characters.")
    private String architecturalStyle;

    @NotBlank(message = "Construction period cannot be blank.")
    @Size(max = 100, message = "Construction period must not exceed 100 characters.")
    private String constructionPeriod;

    @NotBlank(message = "Location cannot be blank.")
    @Size(max = 255, message = "Location must not exceed 255 characters.")
    private String location;

    @NotBlank(message = "Visiting hours cannot be blank.")
    @Size(max = 255, message = "Visiting hours must not exceed 255 characters.")
    private String visitingHours;

    @NotBlank(message = "Entrance fee cannot be blank.")
    @Size(max = 100, message = "Entrance fee must not exceed 100 characters.")
    private String entranceFee;

    @NotBlank(message = "UNESCO declaration cannot be blank.")
    @Size(max = 255, message = "UNESCO declaration must not exceed 255 characters.")
    private String unescoDeclaration;

    @NotNull(message = "Height cannot be null.")
    @Positive(message = "Height must be a positive number.")
    private Double height;

    @NotBlank(message = "Main materials cannot be blank.")
    @Size(max = 255, message = "Main materials must not exceed 255 characters.")
    private String mainMaterials;

    @NotBlank(message = "Curiosities cannot be blank.")
    @Size(max = 1000, message = "Curiosities must not exceed 1000 characters.")
    private String curiosities;

    @NotBlank(message = "Type cannot be blank.")
    @Size(max = 100, message = "Type must not exceed 100 characters.")
    private String type;
}
