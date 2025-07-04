package com.patrigod.evento.entity.dto.output;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.rating.infraestructure.repository.jpa.entity.RatingJpa;

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
public class EventoOutputDto {
    private Long id;

    private CityJpa ciudad;

    private String nombre;

    private List<RatingJpa> puntuaciones = new ArrayList<>();

    private LocalDate fecha;

    private LocalTime horaEvento;

    private String lugar;

    private String informacionEvento;

    private String imagen;

    private String tipoEvento;

    private String organizador;

    private String webOficial;

    private String precio;

    private Integer duracion;
}
