package com.patrigod.ciudad.entity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ciudad")
public class CiudadJpa {

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
