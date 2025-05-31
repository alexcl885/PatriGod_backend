package com.patrigod.patrigod.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControPrueba {
    @GetMapping("/api/admin/test")
public ResponseEntity<?> testAdmin(Authentication auth) {
    return ResponseEntity.ok(auth.getAuthorities());
    
}

}
