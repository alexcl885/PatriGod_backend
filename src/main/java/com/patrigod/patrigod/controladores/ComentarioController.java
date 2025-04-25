package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Comentario;
import com.patrigod.patrigod.servicios.ServiComentario;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {
    @Autowired
    private ServiComentario serviComentario;

    @GetMapping
    public List<Comentario> findAll() {
        return serviComentario.findAll();
    }

    
}
