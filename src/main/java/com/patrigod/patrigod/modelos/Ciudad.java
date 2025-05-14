package com.patrigod.patrigod.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(name = "comunidad_autonoma", nullable = false, length = 255)
    private String comunidadAutonoma;

    @Column(nullable = false, length = 255)
    private String provincia;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_patrimonio")
    private LocalDate fechaPatrimonio;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitud;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitud;

    @Column(name = "imagen_principal", length = 255)
    private String imagenPrincipal;

    private Double puntuacion;
}
