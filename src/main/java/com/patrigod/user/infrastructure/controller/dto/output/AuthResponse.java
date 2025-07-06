package com.patrigod.user.infrastructure.controller.dto.output;

import lombok.Getter;

@Getter
public class AuthResponse {

    private String jwt;

    public AuthResponse(String jwt) { this.jwt = jwt; }
}
