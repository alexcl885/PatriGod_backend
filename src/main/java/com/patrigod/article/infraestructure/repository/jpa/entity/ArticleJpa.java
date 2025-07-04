package com.patrigod.article.infraestructure.repository.jpa.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.evento.entity.entidad.EventoJpa;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.rating.infraestructure.repository.jpa.entity.RatingJpa;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuperBuilder

@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MonumentoJpa.class, name = "monument"),
        @JsonSubTypes.Type(value = ComidaJpa.class, name = "food"),
        @JsonSubTypes.Type(value = EventoJpa.class, name = "event")
})

@Table(name = "article")
public abstract class ArticleJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "fk_article_city"))
    private CityJpa city;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RatingJpa> ratings = new ArrayList<>();

}
