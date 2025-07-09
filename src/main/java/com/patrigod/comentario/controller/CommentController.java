package com.patrigod.comentario.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.comentario.entity.entity.Comment;
import com.patrigod.comentario.service.ServiComentario;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    
    private final ServiComentario serviComentario;

    /**
     * Metodo GET que devuelve todos los comentarios de mi proyecto
     * @return una lista de comentarios
     */
    @GetMapping
    public List<Comment> findAll() {
        return serviComentario.findAll();
    }

    
}
