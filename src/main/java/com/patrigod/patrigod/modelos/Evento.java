package com.patrigod.patrigod.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("evento")
@Data
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
