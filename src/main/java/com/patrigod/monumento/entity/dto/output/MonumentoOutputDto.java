package com.patrigod.monumento.entity.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.puntuacion.entity.entity.Puntuacion;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonumentoOutputDto {
    private Long id;

    private CityJpa ciudad;

    private String nombre;

    private List<Puntuacion> puntuaciones = new ArrayList<>();
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
