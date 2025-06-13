package com.patrigod.patrigod.puntuacion.entity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.patrigod.patrigod.articulo.entity.entity.ArticuloJpa;
import com.patrigod.patrigod.usuario.entity.entity.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    @JsonBackReference // Evita la serialización recursiva
    private ArticuloJpa articulo;

    @Column(nullable = false)
    private Float puntuacion;
}
