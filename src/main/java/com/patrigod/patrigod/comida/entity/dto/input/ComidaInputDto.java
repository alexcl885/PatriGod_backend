package com.patrigod.patrigod.comida.entity.dto.input;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.patrigod.puntuacion.entity.entity.Puntuacion;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComidaInputDto {
    private Long id;

    private Long idCiudad;

    private String nombre;

    private List<Puntuacion> puntuaciones = new ArrayList<>();
    private String imagen;

    private String descripcion;
    private String type;

    private String tipo; 

    private String origen; 

    private String ingredientesPrincipales; 

    private Integer caloriasAprox; 

    private String momentoConsumo; 

    private Boolean aptoVegetarianos; 

    private String acompañamientosRecomendados; 

    private String curiosidades; 

    
}
