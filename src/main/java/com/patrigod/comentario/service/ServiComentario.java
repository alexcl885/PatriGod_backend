package com.patrigod.comentario.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.patrigod.comentario.entity.entity.Comment;
import com.patrigod.comentario.repository.CommentRepositoryJpa;

@Service
@RequiredArgsConstructor
public class ServiComentario {
    
    private final CommentRepositoryJpa commentRepositoryJpa;

    /**
     * Metodo que devuelve todos los comentarios
     * @return todas los comentarios de mi base de datos
     */
    public List<Comment> findAll(){
        System.err.println(commentRepositoryJpa.findAll());
        return commentRepositoryJpa.findAll();
    }


}
