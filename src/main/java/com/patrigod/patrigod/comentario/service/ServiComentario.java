package com.patrigod.patrigod.comentario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrigod.patrigod.comentario.entity.entity.Comentario;
import com.patrigod.patrigod.comentario.repository.RepoComentario;

@Service
public class ServiComentario {
    
    private final RepoComentario repoComentario;

    public ServiComentario(RepoComentario repoComentario){
        this.repoComentario=repoComentario;
    }

    /**
     * Metodo que devuelve todos los comentarios
     * @return todas los comentarios de mi base de datos
     */
    public List<Comentario> findAll(){
        System.err.println(repoComentario.findAll());
        return repoComentario.findAll();
    }


}
