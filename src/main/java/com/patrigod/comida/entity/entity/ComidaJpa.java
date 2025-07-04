package com.patrigod.comida.entity.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@PrimaryKeyJoinColumn(name = "id") // Hereda el ID de Articulo
@JsonTypeName("food")
@Table(name = "food")
public class ComidaJpa extends ArticleJpa {

    @Column(length = 255)
    private String imagen;

    @Column(length = 100)
    private String tipo; 

    @Column(length = 100)
    private String origen; 

    @Column(length = 255)
    private String ingredientesPrincipales; 

    private Integer caloriasAprox; 

    @Column(length = 255)
    private String momentoConsumo; 

    private Boolean aptoVegetarianos; 

    @Column(length = 255)
    private String acompañamientosRecomendados; 

    @Column(columnDefinition = "TEXT")
    private String curiosidades; 
}
