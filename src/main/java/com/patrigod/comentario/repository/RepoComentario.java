package com.patrigod.comentario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.comentario.entity.entity.Comentario;

@Repository
public interface RepoComentario extends JpaRepository<Comentario,Long> {
    
}
