package com.patrigod.usuario.entity.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioActualizacionDTO {
    private String username;
    private String email;
}
