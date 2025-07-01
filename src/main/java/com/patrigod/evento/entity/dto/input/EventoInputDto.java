package com.patrigod.evento.entity.dto.input;

import java.time.LocalDate;
import java.time.LocalTime;
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
@NoArgsConstructor  
@AllArgsConstructor
@Builder
public class EventoInputDto {
    private Long id;

    private Long idCiudad;

    private String nombre;

    private String descripcion; 

    private List<Puntuacion> puntuaciones = new ArrayList<>();

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
    private String type;
    
}
