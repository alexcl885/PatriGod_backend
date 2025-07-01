package com.patrigod.articulo.entity.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.evento.entity.entidad.EventoJpa;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.puntuacion.entity.entity.Puntuacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MonumentoJpa.class, name = "monumento"),
        @JsonSubTypes.Type(value = ComidaJpa.class, name = "comida"),
        @JsonSubTypes.Type(value = EventoJpa.class, name = "evento")
})
@Table(name = "articulo") 
@SuperBuilder
public abstract class ArticuloJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false, foreignKey = @ForeignKey(name = "fk_articulo_ciudad"))
    private CityJpa ciudad;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Puntuacion> puntuaciones = new ArrayList<>();

}
