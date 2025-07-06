package com.patrigod.rating.infrastructure.repository.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;
import com.patrigod.usuario.entity.entity.Usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "rating")
public class RatingJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference // Evita la serialización recursiva
    private ArticleJpa article;

    @Column(nullable = false)
    private Float rating;
}
