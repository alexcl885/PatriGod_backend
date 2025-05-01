package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.Monumento;

@Repository
public interface RepoMonumento extends JpaRepository<Monumento, Long>{
    List<Monumento> findMonumentoByCiudad(Ciudad ciudad);
}
