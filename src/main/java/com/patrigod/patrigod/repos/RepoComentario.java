package com.patrigod.patrigod.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Comentario;

@Repository
public interface RepoComentario extends JpaRepository<Comentario,Long> {
    
}
