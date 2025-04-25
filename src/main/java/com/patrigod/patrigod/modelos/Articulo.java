package com.patrigod.patrigod.modelos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase abstracta base que representa un elemento que puede ser calificado y comentado.
// Se utiliza herencia JOINED para que cada subclase tenga su propia tabla en la base de datos,
// y comparta las columnas comunes con esta tabla 'articulo'.

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@Data
@NoArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, // Usar un nombre en el JSON para identificar el tipo de subclase
    include = JsonTypeInfo.As.EXISTING_PROPERTY, 
    property = "type" // El campo "type" será utilizado para diferenciar las subclases
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Monumento.class, name = "monumento"),
    @JsonSubTypes.Type(value = Comida.class, name = "comida"),
    @JsonSubTypes.Type(value = Evento.class, name = "evento")
    // Añadir otras subclases aquí si las tienes
})
public abstract class Articulo { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id", nullable = false, foreignKey = @ForeignKey(name = "fk_articulo_ciudad"))
    private Ciudad ciudad;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
