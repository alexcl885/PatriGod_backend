package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.DTO.RankingCiudadDTO;

@Repository
public interface RepoCiudad extends JpaRepository<Ciudad,Long>{
    /*@Query("SELECT \n" + //
                "    c.id AS ciudad_id,\n" + //
                "    c.nombre AS ciudad_nombre,\n" + //
                "    AVG(p.puntuacion) AS puntuacion_promedio\n" + //
                "FROM \n" + //
                "    ciudad c\n" + //
                "JOIN \n" + //
                "    articulo a ON a.ciudad_id = c.id\n" + //
                "JOIN \n" + //
                "    puntuacion p ON p.articulo_id = a.id\n" + //
                "LEFT JOIN \n" + //
                "    comida co ON co.articulo_id = a.id\n" + //
                "LEFT JOIN \n" + //
                "    evento e ON e.articulo_id = a.id\n" + //
                "LEFT JOIN \n" + //
                "    monumento m ON m.articulo_id = a.id\n" + //
                "GROUP BY \n" + //
                "    c.id\n" + //
                "ORDER BY \n" + //
                "    puntuacion_promedio DESC ")
    List<RankingCiudadDTO> findRankingCiudadesByPuntuacionPromedio();
    */
}
