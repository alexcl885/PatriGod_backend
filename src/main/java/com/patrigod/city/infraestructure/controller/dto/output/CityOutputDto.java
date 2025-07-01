package com.patrigod.city.infraestructure.controller.dto.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityOutputDto {

    private Long id;

    private String name;

    private String autonomousCommunity;

    private String province;

    private String description;

    private LocalDate heritageDate;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String mainImage;

    private Double rating;
}
