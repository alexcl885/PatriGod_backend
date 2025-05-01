package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.Comida;

@Repository
public interface RepoComida extends JpaRepository<Comida,Long> {
    List<Comida> findComidaByCiudad(Ciudad ciudad);

}
