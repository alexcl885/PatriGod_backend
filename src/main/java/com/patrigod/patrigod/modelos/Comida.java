package com.patrigod.patrigod.modelos;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("comida")
@Getter
@Setter
@NoArgsConstructor
public class Comida extends Articulo {

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
