package com.patrigod.monumento.entity.model;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.ciudad.entity.model.Ciudad;
import com.patrigod.puntuacion.entity.entity.Puntuacion;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Monumento {
    private Long id;

    private Ciudad ciudad;

    private String nombre;

    private List<Puntuacion> puntuaciones = new ArrayList<>();
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
