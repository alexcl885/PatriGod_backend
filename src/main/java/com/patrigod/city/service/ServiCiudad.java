package com.patrigod.city.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.patrigod.city.domain.entity.City;
import org.springframework.stereotype.Service;

import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import com.patrigod.city.infraestructure.controller.dto.output.RankingCityDto;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.city.application.mapper.CityMapper;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import com.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.comida.repository.RepoComida;
import com.patrigod.evento.entity.entidad.EventoJpa;
import com.patrigod.evento.entity.model.Evento;
import com.patrigod.evento.mapper.EventoMapper;
import com.patrigod.evento.repository.RepoEvento;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.monumento.repository.RepoMonumento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiCiudad {

    private final CityMapper cityMapper;
    private final EventoMapper eventoMapper;

    private final CityRepositoryJpa cityRepositoryJpa;
    private final RepoMonumento repoMonumento;
    private final RepoComida repoComida;
    private final RepoEvento repoEvento;


    /**
     * Metodo que devuelve una ciudad mediante el id
     * @param id ciudad 
     * @return una ciudad 
     */
    public City findOneCiudad(Long id){
        CityJpa cityJpa = cityRepositoryJpa.findById(id).orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return cityMapper.toModel(cityJpa);
    }
    /**
     * Metodo que encuentra los monumentos de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de monumentos de la ciudad indicada
     */
    public List<MonumentoJpa> findMonumentosByCiudad(Long idCiudad) {
        Optional<CityJpa> ciudadOptional = cityRepositoryJpa.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            return repoMonumento.findMonumentoByCiudad(ciudadOptional.get());
        }
        return List.of(); 
    }

    /**
     * Metodo que encuentra las comidas de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de comidas de la ciudad indicada
     */
    public List<ComidaJpa> findComidasByCiudad(Long idCiudad) {
        Optional<CityJpa> ciudadOptional = cityRepositoryJpa.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            return repoComida.findComidaByCiudad(ciudadOptional.get());
        }
        return List.of(); 
    }

    /**
     * Metodo que encuentra los eventos de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de eventos de la ciudad indicada
     */
    public List<Evento> findEventosByCiudad(Long idCiudad) {
        Optional<CityJpa> ciudadOptional = cityRepositoryJpa.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            List<EventoJpa> listEventoJpa = repoEvento.findEventoByCiudad(ciudadOptional.get());
            return listEventoJpa.stream()
                    .map(eventoMapper::toModel)
                    .collect(Collectors.toList());
        }
        return List.of(); 
    }
    

    /**
     * Este método realiza un ranking de las ciudades Patrimonio de la Humanidad
     * basándose en la puntuación promedio de sus Eventos, Monumentos y Comidas.
     * 
     * Pasos:
     * 1. Se realiza una consulta SQL para obtener el ranking de las ciudades con 
     *    su puntuación promedio. Esta información se almacena en una lista de 
     *    objetos `RankingCiudadDTO`.
     * 2. Se crea una lista vacía donde se almacenarán las ciudades completas.
     * 3. Se obtiene una lista de los IDs de las ciudades presentes en el ranking.
     * 4. Con los IDs obtenidos, se consultan todas las ciudades correspondientes 
     *    en la base de datos.
     * 5. Se asocia la puntuación promedio de cada ciudad (proveniente del DTO) 
     *    al objeto `Ciudad` y se agrega a la lista final de ciudades.
     * 6. Se devuelve la lista de ciudades, ahora con su puntuación promedio.
     * 
     * @return Lista de ciudades ordenadas por su puntuación promedio de mayor a menor.
     */

    public List<CityJpa> obtenerRankingDeCiudades() {
        List<RankingCityDto> ranking = cityRepositoryJpa.findRankingCiudadesByPuntuacionPromedio();
        List<CityJpa> ciudadesCompletas = new ArrayList<>();
        List<Long> ciudadIds = ranking.stream()
                                      .map(RankingCityDto::getCityId)
                                      .collect(Collectors.toList());
        
        List<CityJpa> ciudades = cityRepositoryJpa.findAllById(ciudadIds); // Obtener las ciudades con esos IDs
        for (RankingCityDto dto : ranking) {
            Optional<CityJpa> ciudadOpt = ciudades.stream()
                                                 .filter(ciudad -> ciudad.getId().equals(dto.getCityId()))
                                                 .findFirst();
            if (ciudadOpt.isPresent()) {
                CityJpa ciudad = ciudadOpt.get();
                ciudad.setRating(dto.getAverageRating());
                ciudadesCompletas.add(ciudad);
            }
        }
        return ciudadesCompletas;
    }    
    /**
     * Devuelve una lista en orden segun la puntuacion de los monumentos
     * de cada ciudad.
     * @return lista de ciudades segun la puntuacion de sus monumentos
     */
    public List<RankingArticleDto> findRankingByMonumento(){
        return cityRepositoryJpa.findRankingByMonument();
    }
    /**
     * Devuelve una lista en orden segun la puntuacion de las comidas
     * de cada ciudad.
     * @return lista de ciudades segun la puntuacion de sus comidas
     */
    public List<RankingArticleDto> findRankingByComida(){
        return cityRepositoryJpa.findRankingByFood();
    }
    /**
     * Devuelve una lista en orden segun la puntuacion de los eventos
     * de cada ciudad.
     * @return lista de ciudades segun la puntuacion de sus eventos
     */
    public List<RankingArticleDto> findRankignByEvento(){
        return cityRepositoryJpa.findRankingByEvent();
    }

    
}
