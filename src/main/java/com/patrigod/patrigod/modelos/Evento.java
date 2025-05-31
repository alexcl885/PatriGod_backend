package com.patrigod.patrigod.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento extends Articulo {

    private LocalDate fecha;

    private LocalTime horaEvento;

    private String lugar;

    @Column(length = 1000)
    private String informacionEvento;

    private String imagen;

    private String tipoEvento;

    private String organizador;

    private String webOficial;

    private String precio;

    private Integer duracion; 
}
