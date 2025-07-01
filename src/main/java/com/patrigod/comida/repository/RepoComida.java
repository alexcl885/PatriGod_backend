package com.patrigod.comida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.comida.entity.entity.ComidaJpa;

@Repository
public interface RepoComida extends JpaRepository<ComidaJpa,Long> {
    List<ComidaJpa> findComidaByCiudad(CiudadJpa ciudad);

}
