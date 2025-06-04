package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.DTO.RankingArticuloDTO;
import com.patrigod.patrigod.DTO.RankingCiudadDTO;
import com.patrigod.patrigod.modelos.Ciudad;

@Repository
public interface RepoCiudad extends JpaRepository<Ciudad, Long> {
    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de todos los
     * artículos
     * (excluyendo específicamente comidas, eventos y monumentos), ordenadas de
     * mayor a menor puntuación.
     * 
     * - Solo se consideran artículos que tienen puntuaciones asociadas.
     * - Se calcula la puntuación promedio de los artículos de cada ciudad,
     * excluyendo los artículos
     * que son de tipo comida, evento o monumento (mediante `LEFT JOIN`).
     * - La puntuación promedio se calcula utilizando `AVG(p.puntuacion)` y se
     * maneja con `COALESCE` para
     * tratar valores nulos de puntuación como 0.
     * - Se asigna un número de posición a cada ciudad utilizando `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación promedio de los artículos asociados a cada ciudad,
     *         excluyendo los artículos
     *         de tipo comida, evento o monumento.
     */

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
            "ORDER BY puntuacion_promedio DESC", nativeQuery = true)
    List<RankingCiudadDTO> findRankingCiudadesByPuntuacionPromedio();

    

    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de los
     * artículos
     * clasificados como monumentos, ordenadas de mayor a menor puntuación.
     * 
     * - Solo se consideran los artículos que son monumentos (mediante JOIN con la
     * tabla `monumento`).
     * - Se incluyen los monumentos que tienen al menos una puntuación asociada.
     * - Se usa `COALESCE(p.puntuacion, 0)` para tratar los valores nulos de las
     * puntuaciones como 0 al calcular la media.
     * - Se asigna un número de posición a cada ciudad utilizando `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación media de los monumentos asociados a cada ciudad.
     */

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
            "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
            "FROM ciudad c " +
            "JOIN articulo a ON c.id = a.ciudad_id " +
            "JOIN monumento m ON a.id = m.id " +
            "JOIN puntuacion p ON a.id = p.articulo_id " +
            "GROUP BY c.id " +
            "ORDER BY puntuacion_media DESC", nativeQuery = true)
    List<RankingArticuloDTO> findRankingByMonumento();

    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de los
     * artículos
     * clasificados como comidas, ordenadas de mayor a menor puntuación.
     * 
     * - Solo se consideran los artículos que son comidas (mediante JOIN con la
     * tabla `comida`).
     * - Se incluyen los artículos que tienen al menos una puntuación asociada.
     * - Se usa `COALESCE(p.puntuacion, 0)` para tratar los valores nulos de las
     * puntuaciones como 0 al calcular la media.
     * - Se asigna un número de posición a cada ciudad utilizando `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación media de los artículos que son comidas asociados a
     *         cada ciudad.
     */

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
            "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
            "FROM ciudad c " +
            "JOIN articulo a ON c.id = a.ciudad_id " +
            "JOIN comida co ON a.id = co.id " +
            "JOIN puntuacion p ON a.id = p.articulo_id " +
            "GROUP BY c.id " +
            "ORDER BY puntuacion_media DESC", nativeQuery = true)
    List<RankingArticuloDTO> findRankingByComida();

    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de los
     * artículos
     * clasificados como eventos, ordenadas de mayor a menor puntuación.
     *
     * - Solo se consideran los artículos que son eventos (mediante JOIN con la
     * tabla `evento`).
     * - Solo se consideran los eventos que tienen al menos una puntuación asociada.
     * - Se usa `COALESCE(p.puntuacion, 0)` para tratar posibles valores nulos como
     * 0 en el cálculo de la media.
     * - Se asigna un número de posición a cada ciudad mediante `ROW_NUMBER()`.
     * 
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación media de los eventos asociados a cada una.
     */

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
            "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
            "FROM ciudad c " +
            "JOIN articulo a ON c.id = a.ciudad_id " +
            "JOIN evento e ON a.id = e.id " +
            "JOIN puntuacion p ON a.id = p.articulo_id " +
            "GROUP BY c.id " +
            "ORDER BY puntuacion_media DESC", nativeQuery = true)
    List<RankingArticuloDTO> findRankingByEvento();

}
