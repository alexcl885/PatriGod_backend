package com.patrigod.user.infrastructure.controller.dto.input;

import com.patrigod.user.domain.enums.TypeUser;

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
public class ChangeRoleInputDto {
    private String username;
    private TypeUser type;
}
