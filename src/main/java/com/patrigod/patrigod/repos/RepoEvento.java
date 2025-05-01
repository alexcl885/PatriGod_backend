package com.patrigod.patrigod.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.Evento;

@Repository
public interface RepoEvento extends JpaRepository<Evento, Long> {
    List<Evento> findEventoByCiudad(Ciudad ciudad);

}
