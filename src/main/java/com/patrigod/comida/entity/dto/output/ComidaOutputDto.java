package com.patrigod.comida.entity.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.domain.entity.City;
import com.patrigod.rating.infraestructure.repository.jpa.entity.RatingJpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComidaOutputDto {

    private Long id;

    private City city;

    private String nombre;

    private List<RatingJpa> puntuaciones = new ArrayList<>();
    private String imagen;

    private String tipo; 

    private String origen; 

    private String ingredientesPrincipales; 

    private Integer caloriasAprox; 

    private String momentoConsumo; 

    private Boolean aptoVegetarianos; 

    private String acompañamientosRecomendados; 

    private String curiosidades; 
    private String descripcion;
    private String type;

    
}
