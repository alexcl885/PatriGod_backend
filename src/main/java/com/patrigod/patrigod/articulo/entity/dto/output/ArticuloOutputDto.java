package com.patrigod.patrigod.articulo.entity.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.patrigod.puntuacion.entity.entity.Puntuacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloOutputDto {
    private Long id;

    private CiudadJpa ciudad;

    private String nombre;

    private String descripcion;

    private List<Puntuacion> puntuaciones = new ArrayList<>();

}
