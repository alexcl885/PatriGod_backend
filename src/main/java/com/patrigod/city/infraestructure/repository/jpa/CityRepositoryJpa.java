package com.patrigod.city.infraestructure.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import com.patrigod.city.infraestructure.controller.dto.output.RankingCityDto;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;

@Repository
public interface CityRepositoryJpa extends JpaRepository<CityJpa, Long> {
    // TODO ARRELGAR LAS QUERYS PORQUE VOY A CAMBIAR TODO A INGLES
    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de todos los
     * artículos
     * (excluyendo específicamente comidas, eventos y monumentos), ordenadas de
     * mayor a menor puntuación.
     * 
     * - Solo se consideran artículos que tienen puntuaciones asociadas.
     * - Se calcula la puntuación promedio de los artículos de cada ciudad,
     * excluyendo los artículos
     * que son de tipo food, event o monument (mediante `LEFT JOIN`).
     * - La puntuación promedio se calcula utilizando `AVG(p.ratingJpa)` y se
     * maneja con `COALESCE` para
     * tratar valores nulos de puntuación como 0.
     * - Se asigna un número de posición a cada ciudad utilizando `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación promedio de los artículos asociados a cada ciudad,
     *         excluyendo los artículos
     *         de tipo food, event o monument.
     */

    @Query(value = """
    SELECT\s
        ROW_NUMBER() OVER (ORDER BY average_rating DESC) AS position,
        city_id,
        city_name,
        average_rating
    FROM (
        SELECT\s
            c.id AS city_id,
            c.name AS city_name,
            AVG(r.rating) AS average_rating
        FROM city c
        JOIN article a ON a.city_id = c.id
        JOIN rating r ON r.article_id = a.id
        LEFT JOIN food f ON f.id = a.id
        LEFT JOIN event e ON e.id = a.id
        LEFT JOIN monument m ON m.id = a.id
        GROUP BY c.id, c.name
    ) AS ranking
    ORDER BY average_rating DESC
   \s""", nativeQuery = true)
    List<RankingCityDto> findCityRankingByAverageRating();




    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de los
     * artículos
     * clasificados como monumentos, ordenadas de mayor a menor puntuación.
     * 
     * - Solo se consideran los artículos que son monumentos (mediante JOIN con la
     * tabla `monument`).
     * - Se incluyen los monumentos que tienen al menos una puntuación asociada.
     * - Se usa `COALESCE(p.ratingJpa, 0)` para tratar los valores nulos de las
     * puntuaciones como 0 al calcular la media.
     * - Se asigna un número de posición a cada ciudad utilizando `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación media de los monumentos asociados a cada ciudad.
     */

    @Query(value = """
    SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(s.rating, 0)) DESC) AS position,
           c.id AS city_id,
           c.name AS city_name,
           AVG(COALESCE(s.rating, 0)) AS average_score
    FROM city c
    JOIN article a ON c.id = a.city_id
    JOIN monument m ON a.id = m.id
    JOIN rating s ON a.id = s.article_id
    GROUP BY c.id
    ORDER BY average_score DESC
    """, nativeQuery = true)
    List<RankingArticleDto> findRankingByMonument();

    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de los
     * artículos
     * clasificados como comidas, ordenadas de mayor a menor puntuación.
     *
     * - Solo se consideran los artículos que son comidas (mediante JOIN con la
     * tabla `food`).
     * - Se incluyen los artículos que tienen al menos una puntuación asociada.
     * - Se usa `COALESCE(p.ratingJpa, 0)` para tratar los valores nulos de las
     * puntuaciones como 0 al calcular la media.
     * - Se asigna un número de posición a cada ciudad utilizando `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación media de los artículos que son comidas asociados a
     *         cada ciudad.
     */

    @Query(value = """
    SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(s.rating, 0)) DESC) AS position,
           c.id AS city_id,
           c.name AS city_name,
           AVG(COALESCE(s.rating, 0)) AS average_score
    FROM city c
    JOIN article a ON c.id = a.city_id
    JOIN food f ON a.id = f.id
    JOIN rating s ON a.id = s.article_id
    GROUP BY c.id
    ORDER BY average_score DESC
    """, nativeQuery = true)
    List<RankingArticleDto> findRankingByFood();

    /**
     * Obtiene un ranking de ciudades basado en la puntuación promedio de los
     * artículos
     * clasificados como eventos, ordenadas de mayor a menor puntuación.
     *
     * - Solo se consideran los artículos que son eventos (mediante JOIN con la
     * tabla `event`).
     * - Solo se consideran los eventos que tienen al menos una puntuación asociada.
     * - Se usa `COALESCE(p.ratingJpa, 0)` para tratar posibles valores nulos como
     * 0 en el cálculo de la media.
     * - Se asigna un número de posición a cada ciudad mediante `ROW_NUMBER()`.
     *
     * @return Una lista con la posición en el ranking, el ID de la ciudad, su
     *         nombre
     *         y la puntuación media de los eventos asociados a cada una.
     */

    @Query(value = """
    SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(s.rating, 0)) DESC) AS position,
           c.id AS city_id,
           c.name AS city_name,
           AVG(COALESCE(s.rating, 0)) AS average_score
    FROM city c
    JOIN article a ON c.id = a.city_id
    JOIN event e ON a.id = e.id
    JOIN rating s ON a.id = s.article_id
    GROUP BY c.id
    ORDER BY average_score DESC
    """, nativeQuery = true)
    List<RankingArticleDto> findRankingByEvent();

}
