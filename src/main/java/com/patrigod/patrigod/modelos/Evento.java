package com.patrigod.patrigod.modelos;

import jakarta.persistence.*;
import lombok.ToString;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("evento")
@Entity
public class Evento extends Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id", nullable = false, foreignKey = @ForeignKey(name = "fk_evento_ciudad"))
    @ToString.Exclude
    private Ciudad ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_id", nullable = false, foreignKey = @ForeignKey(name = "fk_evento_articulo"))
    @ToString.Exclude
    private Articulo articulo;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;
}
