package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Puntuacion;
import com.patrigod.patrigod.repos.RepoPuntuacion;

@Service
public class ServiPuntuacion {
    @Autowired
    private RepoPuntuacion repoPuntuacion;

    public List<Puntuacion> findAll(){
        System.err.println(repoPuntuacion.findAll());
        return repoPuntuacion.findAll();
    }

    public Puntuacion savePuntuacion(Puntuacion puntuacion) {
        Long usuarioId = puntuacion.getUsuario().getId();
        Long articuloId = puntuacion.getArticulo().getId();
    
        Optional<Puntuacion> existente = repoPuntuacion.findByUsuarioIdAndArticuloId(usuarioId, articuloId);
    
        if (existente.isPresent()) {
            throw new IllegalStateException("Ya has puntuado este artículo.");
        }
    
        return repoPuntuacion.save(puntuacion);
    }
    
}
