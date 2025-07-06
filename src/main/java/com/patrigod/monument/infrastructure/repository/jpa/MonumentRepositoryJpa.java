package com.patrigod.monument.infrastructure.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;

@Repository
public interface MonumentRepositoryJpa extends JpaRepository<MonumentJpa, Long>{
    List<MonumentJpa> findMonumentByCity(CityJpa ciudad);
}
