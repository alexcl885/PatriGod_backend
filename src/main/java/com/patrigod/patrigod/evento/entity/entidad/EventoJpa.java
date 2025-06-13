package com.patrigod.patrigod.evento.entity.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.patrigod.articulo.entity.entity.ArticuloJpa;

@Entity
@PrimaryKeyJoinColumn(name = "id") // Hereda el ID de Articulo
@JsonTypeName("evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evento")
@SuperBuilder
public class EventoJpa extends ArticuloJpa {

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
