package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Comentario;
import com.patrigod.patrigod.repos.RepoComentario;

@Service
public class ServiComentario {
    @Autowired
    private RepoComentario repoComentario;

    public List<Comentario> findAll(){
        System.err.println(repoComentario.findAll());
        return repoComentario.findAll();
    }


}
