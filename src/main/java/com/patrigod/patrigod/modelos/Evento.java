package com.patrigod.patrigod.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("evento")
@Data
@NoArgsConstructor
public class Evento extends Articulo {
    private LocalDate fecha;
}
