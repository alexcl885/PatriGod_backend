package com.patrigod.patrigod.usuario.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.usuario.entity.entity.Usuario;
import com.patrigod.patrigod.usuario.service.ServiUsuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/admin/usuario")
public class UsuarioAdminController {
    @Autowired
    ServiUsuario serviUsuario;

    @GetMapping
    public List<Usuario> findAllUsers() {
        return serviUsuario.findAll();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstadoUsuario(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Boolean activo = body.get("activo");
        if (activo == null) {
            return ResponseEntity.badRequest().body("El campo 'activo' es obligatorio.");
        }

        boolean actualizado = serviUsuario.cambiarEstado(id, activo);
        if (actualizado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{username}")
    public List<Usuario> findUsersByUsername(@PathVariable String username) {
        return serviUsuario.findByName(username);
    }


    
    
}
