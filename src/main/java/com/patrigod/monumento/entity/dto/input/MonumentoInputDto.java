package com.patrigod.monumento.entity.dto.input;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.puntuacion.entity.entity.Puntuacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonumentoInputDto {
    private Long id;

    private Long idCiudad;

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

    private String type;
}
