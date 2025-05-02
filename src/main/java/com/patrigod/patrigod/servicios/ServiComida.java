package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.repos.RepoComida;

@Service
public class ServiComida {
    @Autowired
    private RepoComida repoComida;

    public List<Comida> findAll(){
        System.err.println(repoComida.findAll());
        return repoComida.findAll();
    }

    public Optional<Comida> findComida(Long id){
        return repoComida.findById(id);
    }

}
