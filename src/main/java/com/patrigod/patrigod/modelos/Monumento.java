package com.patrigod.patrigod.modelos;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
// Subclase de Articulo que representa un monumento.
// Tendrá su propia tabla 'monumento' relacionada con 'articulo' por la clave primaria (id).
@JsonTypeName("monumento")
@Entity
public class Monumento extends Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id", nullable = false, foreignKey = @ForeignKey(name = "fk_monumento_ciudad"))
    @ToString.Exclude
    private Ciudad ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_id", nullable = false, foreignKey = @ForeignKey(name = "fk_monumento_articulo"))
    @ToString.Exclude
    private Articulo articulo;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(length = 255)
    private String imagen;
}
