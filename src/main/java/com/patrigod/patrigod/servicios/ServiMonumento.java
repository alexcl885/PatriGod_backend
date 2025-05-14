package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.repos.RepoMonumento;

@Service
public class ServiMonumento {
    @Autowired
    private RepoMonumento repoMonumento;

    public List<Monumento> findAll(){
        System.err.println(repoMonumento.findAll());
        return repoMonumento.findAll();
    }

    public Optional<Monumento> findMonumento(Long id){
        return repoMonumento.findById(id);
    }

    public Monumento saveMonumento(Monumento monumento){
        return repoMonumento.save(monumento);
    }



}
