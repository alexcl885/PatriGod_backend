package com.patrigod.city.infraestructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "city")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "autonomous_community", nullable = false, length = 255)
    private String autonomousCommunity;

    @Column(nullable = false, length = 255)
    private String province;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "heritage_date")
    private LocalDate heritageDate;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;


    @Column(name = "main_image", length = 255)
    private String mainImage;

    private Double rating;
}
