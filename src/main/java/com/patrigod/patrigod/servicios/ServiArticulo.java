package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Articulo;
import com.patrigod.patrigod.repos.RepoArticulo;

@Service
public class ServiArticulo {
    @Autowired
    private RepoArticulo repoArticulo;

    public List<Articulo> findAll(){
        System.err.println(repoArticulo.findAll());
        return repoArticulo.findAll();
    }
    
}
