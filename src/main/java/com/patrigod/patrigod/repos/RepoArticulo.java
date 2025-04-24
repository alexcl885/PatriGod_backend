package com.patrigod.patrigod.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Articulo;

@Repository
public interface RepoArticulo extends JpaRepository<Articulo,Long>{
    
}
