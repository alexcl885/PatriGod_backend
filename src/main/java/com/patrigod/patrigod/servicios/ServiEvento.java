package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.repos.RepoEvento;

@Service
public class ServiEvento {
    @Autowired
    private RepoEvento repoEvento;

    public List<Evento> findAll(){
        System.err.println(repoEvento.findAll());
        return repoEvento.findAll();
    }

    public Optional<Evento> findEvento(Long id){
        return repoEvento.findById(id);
    }


}
