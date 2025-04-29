package com.patrigod.patrigod.modelos;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("comida")
@Data
@NoArgsConstructor
public class Comida extends Articulo {
    @Column(length = 255)
    private String imagen;
}
