package com.patrigod.comentario.entity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patrigod.articulo.entity.entity.ArticuloJpa;
import com.patrigod.usuario.entity.entity.Usuario;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_comentario_usuario"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_comentario_articulo"))
    private ArticuloJpa articulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();
}
 