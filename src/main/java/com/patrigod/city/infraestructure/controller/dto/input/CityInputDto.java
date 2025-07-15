package com.patrigod.city.infraestructure.controller.dto.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityInputDto {

    private Long id;

    @NotBlank
    private String name;

    private String autonomousCommunity;

    private String province;

    @NotBlank
    private String description;

    private LocalDate heritageDate;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @NotBlank    
    private String mainImage;

    private Double rating;
}
