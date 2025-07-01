package com.patrigod.usuario.entity.dto.input;

import com.patrigod.usuario.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CambiarRolDto {
    private String username;
    private TipoUsuario tipo;
}
