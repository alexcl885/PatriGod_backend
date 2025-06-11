package com.patrigod.patrigod.comida.entity.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.patrigod.ciudad.entity.model.Ciudad;
import com.patrigod.patrigod.puntuacion.entity.entity.Puntuacion;

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

    private Ciudad ciudad;

    private String nombre;

    private List<Puntuacion> puntuaciones = new ArrayList<>();
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
