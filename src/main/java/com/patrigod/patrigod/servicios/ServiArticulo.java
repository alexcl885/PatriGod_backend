package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Articulo;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.repos.RepoArticulo;
import com.patrigod.patrigod.repos.RepoMonumento;

@Service
public class ServiArticulo {
    @Autowired
    private RepoArticulo repoArticulo;

    @Autowired
    private RepoMonumento repoMonumento;

    public List<Articulo> findAll(){
        System.err.println(repoArticulo.findAll());
        return repoArticulo.findAll();
    }
    public List<Monumento> findAllMonumentos() {
        return repoMonumento.findAll();  
    }
    
    public Articulo saveArticulo(Articulo articulo) {
        return repoArticulo.save(articulo);  
    }
    public List<Articulo> getAllArticulos() {
        List<Articulo> articulos = repoArticulo.findAll();
        for (Articulo articulo : articulos) {
            System.out.println("ID artículo recuperado: " + articulo.getId()); 
        }
        return articulos;
    }
    
}
    

