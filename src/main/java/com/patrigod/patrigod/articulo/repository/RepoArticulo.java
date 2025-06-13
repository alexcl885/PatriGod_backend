package com.patrigod.patrigod.articulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.articulo.entity.entity.ArticuloJpa;

@Repository
public interface RepoArticulo extends JpaRepository<ArticuloJpa,Long>{
    
}
