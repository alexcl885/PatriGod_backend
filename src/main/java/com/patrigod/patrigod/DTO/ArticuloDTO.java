package com.patrigod.patrigod.DTO;

import com.patrigod.patrigod.modelos.Ciudad;

import lombok.Data;

@Data
public class ArticuloDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String type;
    private Ciudad ciudad;
}