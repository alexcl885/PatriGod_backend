package com.patrigod.patrigod.modelos;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Monumento.class, name = "monumento"),
        @JsonSubTypes.Type(value = Comida.class, name = "comida"),
        @JsonSubTypes.Type(value = Evento.class, name = "evento")
})
public abstract class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false, foreignKey = @ForeignKey(name = "fk_articulo_ciudad"))
    private Ciudad ciudad;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    

}
