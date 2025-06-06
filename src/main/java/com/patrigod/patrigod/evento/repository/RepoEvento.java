package com.patrigod.patrigod.evento.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.patrigod.evento.entity.entidad.EventoJpa;

@Repository
public interface RepoEvento extends JpaRepository<EventoJpa, Long> {
    List<EventoJpa> findEventoByCiudad(CiudadJpa ciudad);

}
