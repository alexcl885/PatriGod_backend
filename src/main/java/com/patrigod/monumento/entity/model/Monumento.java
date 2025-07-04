package com.patrigod.monumento.entity.model;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.domain.entity.City;
import com.patrigod.rating.infraestructure.repository.jpa.entity.RatingJpa;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Monumento {
    private Long id;

    private City city;

    private String nombre;

    private List<RatingJpa> puntuaciones = new ArrayList<>();
    private String descripcion;

    private String imagen;

    private String estiloArquitectonico;     

    private String epocaConstruccion;        

    private String ubicacion;                

    private String horarioVisitas;           

    private String precioEntrada;            

    private String declaracionUnesco;        

    private Double altura;                   

    private String materialesPrincipales;    

    private String curiosidades;  
    
}
