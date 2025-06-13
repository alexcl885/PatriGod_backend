package com.patrigod.patrigod.comida.entity.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.patrigod.articulo.entity.entity.ArticuloJpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "comida")
@PrimaryKeyJoinColumn(name = "id") // Hereda el ID de Articulo
@JsonTypeName("comida")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ComidaJpa extends ArticuloJpa {

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
