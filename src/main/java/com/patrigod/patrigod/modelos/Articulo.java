package com.patrigod.patrigod.modelos;

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
