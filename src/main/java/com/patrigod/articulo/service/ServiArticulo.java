package com.patrigod.articulo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.articulo.entity.entity.ArticuloJpa;
import com.patrigod.articulo.repository.RepoArticulo;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.monumento.repository.RepoMonumento;

@Service
public class ServiArticulo {
    @Autowired
    private RepoArticulo repoArticulo;

    @Autowired
    private RepoMonumento repoMonumento;

    public List<ArticuloJpa> findAll(){
        System.err.println(repoArticulo.findAll());
        return repoArticulo.findAll();
    }
    public List<MonumentoJpa> findAllMonumentos() {
        return repoMonumento.findAll();  
    }
    
    public ArticuloJpa saveArticulo(ArticuloJpa articulo) {
        return repoArticulo.save(articulo);  
    }
    public List<ArticuloJpa> getAllArticulos() {
        List<ArticuloJpa> articulos = repoArticulo.findAll();
        for (ArticuloJpa articulo : articulos) {
            System.out.println("ID artículo recuperado: " + articulo.getId()); 
        }
        return articulos;
    }
    
}
    

