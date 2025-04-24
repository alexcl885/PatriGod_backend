package com.patrigod.patrigod.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Monumento;

@Repository
public interface RepoMonumento extends JpaRepository<Monumento, Long>{
    
}
