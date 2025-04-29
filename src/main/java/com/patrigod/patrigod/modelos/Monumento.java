package com.patrigod.patrigod.modelos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@PrimaryKeyJoinColumn(name = "id")  // <- Indica que usa el mismo PK de Articulo
@JsonTypeName("monumento")
@Data
@NoArgsConstructor
public class Monumento extends Articulo {

    // ¡Aquí sólo los atributos propios de Monumento!
    @Column(length = 255)
    private String imagen;
}
