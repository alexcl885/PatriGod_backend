package com.patrigod.monumento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.monumento.entity.entity.MonumentoJpa;

@Repository
public interface RepoMonumento extends JpaRepository<MonumentoJpa, Long>{
    List<MonumentoJpa> findMonumentoByCiudad(CiudadJpa ciudad);
}
