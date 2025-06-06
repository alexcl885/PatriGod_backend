package com.patrigod.patrigod.ciudad.entity.dto.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiudadInputDto {
    private Long id;

    private String nombre;

    private String comunidadAutonoma;

    private String provincia;

    private String descripcion;

    private LocalDate fechaPatrimonio;

    private BigDecimal latitud;

    private BigDecimal longitud;

    private String imagenPrincipal;

    private Double puntuacion;
}
