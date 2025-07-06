package com.patrigod.monument.infrastructure.repository.jpa.entity;

import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("monument")
@Table(name = "monument")
public class MonumentJpa extends ArticleJpa {

    @Column(length = 255)
    private String imagen;

    private String architecturalStyle;

    private String constructionPeriod;

    private String location;

    private String visitingHours;

    private String entranceFee;

    private String unescoDeclaration;

    private Double height;

    private String mainMaterials;

    @Column(length = 1000)
    private String curiosities;
}
