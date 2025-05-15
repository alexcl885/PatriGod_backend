package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Comentario;
import com.patrigod.patrigod.servicios.ServiComentario;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {
    
    private final ServiComentario serviComentario;

    public ComentarioController(ServiComentario serviComentario){
        this.serviComentario=serviComentario;
    }
    /**
     * Metodo GET que devuelve todos los comentarios de mi proyecto
     * @return una lista de comentarios
     */
    @GetMapping
    public List<Comentario> findAll() {
        return serviComentario.findAll();
    }

    
}
