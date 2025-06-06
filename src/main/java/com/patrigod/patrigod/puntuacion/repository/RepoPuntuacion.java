package com.patrigod.patrigod.puntuacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.puntuacion.entity.entity.Puntuacion;

@Repository
public interface RepoPuntuacion extends JpaRepository<Puntuacion, Long> {
    
    Optional<Puntuacion> findByUsuarioIdAndArticuloId(Long usuarioId, Long articuloId);

}
