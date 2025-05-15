package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Comentario;
import com.patrigod.patrigod.repos.RepoComentario;

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
