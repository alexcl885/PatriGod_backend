package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.DTO.RankingCiudadDTO;
import com.patrigod.patrigod.modelos.Ciudad;

@Repository
public interface RepoCiudad extends JpaRepository<Ciudad,Long>{
    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY puntuacion_promedio DESC) AS posicion, " +
    "ciudad_id, ciudad_nombre, puntuacion_promedio " +
    "FROM (SELECT c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(p.puntuacion) AS puntuacion_promedio " +
    "FROM ciudad c " +
    "JOIN articulo a ON a.ciudad_id = c.id " +
    "JOIN puntuacion p ON p.articulo_id = a.id " +
    "LEFT JOIN comida co ON co.id = a.id " +
    "LEFT JOIN evento e ON e.id = a.id " +
    "LEFT JOIN monumento m ON m.id = a.id " +
    "GROUP BY c.id, c.nombre) AS ranking " +
    "ORDER BY puntuacion_promedio DESC",
nativeQuery = true)
List<RankingCiudadDTO> findRankingCiudadesByPuntuacionPromedio();

    
}
