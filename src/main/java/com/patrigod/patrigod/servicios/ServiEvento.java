package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.repos.RepoEvento;

@Service
public class ServiEvento {
    
    private final RepoEvento repoEvento;

    public ServiEvento(RepoEvento repoEvento) {
        this.repoEvento = repoEvento;
    }

    /**
     * Metodo que devuelve todos los eventos que hay
     * @return una lista de eventos
     */
    public List<Evento> findAll(){
        System.err.println(repoEvento.findAll());
        return repoEvento.findAll();
    }
    /**
     * Metodo que devuelve un evento sugerido por el usuario
     * @param id identificador del evento
     * @return un evento segun un id
     */
    public Optional<Evento> findEvento(Long id){
        return repoEvento.findById(id);
    }

    public Evento saveEvento(Evento evento){
        return repoEvento.save(evento);
    }

    public void deleteEventoById(Long id){
        repoEvento.deleteById(id);
    }


}
