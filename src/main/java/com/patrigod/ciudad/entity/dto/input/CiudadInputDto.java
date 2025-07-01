package com.patrigod.ciudad.entity.dto.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiudadInputDto {
    private Long id;

    @NotBlank
    private String nombre;

    private String comunidadAutonoma;

    private String provincia;

    @NotBlank
    private String descripcion;

    private LocalDate fechaPatrimonio;

    private BigDecimal latitud;

    private BigDecimal longitud;

    @NotBlank    
    private String imagenPrincipal;

    private Double puntuacion;
}
