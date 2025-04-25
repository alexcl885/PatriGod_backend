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
    return repoMonumento.findAll();  // Esto solo debería devolver Monumentos, no Articulos
}
    
}
