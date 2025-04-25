package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.servicios.ServiUsuario;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private ServiUsuario serviUsuario;

    @GetMapping
    public List<Usuario> findAll() {
        return serviUsuario.findAll();
    }
}
