package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.DTO.RankingArticuloDTO;
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

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
        "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
        "FROM ciudad c " +
        "JOIN articulo a ON c.id = a.ciudad_id " +
        "JOIN monumento m ON a.id = m.id " +
        "JOIN puntuacion p ON a.id = p.articulo_id " +
        "GROUP BY c.id " +
        "ORDER BY puntuacion_media DESC", 
        nativeQuery = true)
    List<RankingArticuloDTO> findRankingByMonumento();

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
        "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
        "FROM ciudad c " +
        "JOIN articulo a ON c.id = a.ciudad_id " +
        "JOIN comida co ON a.id = co.id " +
        "JOIN puntuacion p ON a.id = p.articulo_id " +
        "GROUP BY c.id " +
        "ORDER BY puntuacion_media DESC", 
        nativeQuery = true)
    List<RankingArticuloDTO> findRankingByComida();

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
        "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
        "FROM ciudad c " +
        "JOIN articulo a ON c.id = a.ciudad_id " +
        "JOIN evento e ON a.id = e.id " +
        "JOIN puntuacion p ON a.id = p.articulo_id " +
        "GROUP BY c.id " +
        "ORDER BY puntuacion_media DESC", 
        nativeQuery = true)
    List<RankingArticuloDTO> findRankingByEvento();



    
}
