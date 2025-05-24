package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Puntuacion;
import com.patrigod.patrigod.repos.RepoPuntuacion;

@Service
public class ServiPuntuacion {
    
    private final RepoPuntuacion repoPuntuacion;

    public ServiPuntuacion(RepoPuntuacion repoPuntuacion) {
        this.repoPuntuacion = repoPuntuacion;
    }

    /**
     * 
     * @return todas las puntuaciones
     */
    public List<Puntuacion> findAll(){
        return repoPuntuacion.findAll();
    }
    /**
     * Metodo que añade una nueva puntuacion de un articulo
     * por lo que si existe se borra y se añade una nueva haciendo 
     * como si fuera una actualizacion de la puntuacion.
     * 
     * @param puntuacion nueva puntuacion a añadir
     * @return una nueva puntuación
     */
    public Puntuacion savePuntuacion(Puntuacion puntuacion) {
        Long usuarioId = puntuacion.getUsuario().getId();
        Long articuloId = puntuacion.getArticulo().getId();
    
        Optional<Puntuacion> existente = repoPuntuacion.findByUsuarioIdAndArticuloId(usuarioId, articuloId);
    
        existente.ifPresent(p -> repoPuntuacion.deleteById(p.getId()));
    
        return repoPuntuacion.save(puntuacion);
    }
    
    
}
