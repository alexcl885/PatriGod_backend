package com.patrigod.comentario.entity.entity;

import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;

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
    private UserJpa userJpa;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_comentario_articulo"))
    private ArticleJpa articulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();
}
 