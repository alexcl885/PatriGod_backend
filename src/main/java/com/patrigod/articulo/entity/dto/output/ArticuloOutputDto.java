package com.patrigod.articulo.entity.dto.output;

import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.puntuacion.entity.entity.Puntuacion;

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

    private CityJpa ciudad;

    private String nombre;

    private String descripcion;

    private List<Puntuacion> puntuaciones = new ArrayList<>();

}
