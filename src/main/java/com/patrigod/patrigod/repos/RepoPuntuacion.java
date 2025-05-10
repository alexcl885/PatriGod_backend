package com.patrigod.patrigod.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Puntuacion;

@Repository
public interface RepoPuntuacion extends JpaRepository<Puntuacion, Long> {
    
    Optional<Puntuacion> findByUsuarioIdAndArticuloId(Long usuarioId, Long articuloId);

}
