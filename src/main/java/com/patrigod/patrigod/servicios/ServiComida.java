package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.repos.RepoComida;

import jakarta.transaction.Transactional;

@Service
public class ServiComida {
    
    private final RepoComida repoComida;

    public ServiComida(RepoComida repoComida) {
        this.repoComida = repoComida;
    }

    /**
     *  Metodo que devuelve todas las comidas
     * @return todas las comidas
     */
    public List<Comida> findAll(){
        System.err.println(repoComida.findAll());
        return repoComida.findAll();
    }

    /**
     * Metodo que busca una comida por su id
     * @param id identificador de la comida
     * @return una comida que pida el usuario
     */
    public Optional<Comida> findComida(Long id){
        return repoComida.findById(id);
    }

    public Comida saveComida(Comida comida){
        return repoComida.save(comida);
    }

    @Transactional
    public void deleteComidaById(Long id){
        Comida comida = repoComida.findById(id)
                .orElseThrow(() -> new RuntimeException("Comida no encontrada con id: " + id));
        repoComida.delete(comida);
    }

}
