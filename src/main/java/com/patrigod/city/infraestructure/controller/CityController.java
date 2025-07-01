package com.patrigod.city.infraestructure.controller;

import java.util.List;

import com.patrigod.city.application.*;
import com.patrigod.city.domain.entity.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.city.infraestructure.controller.dto.output.CityOutputDto;
import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.city.application.mapper.CityMapper;
import com.patrigod.city.service.ServiCiudad;
import com.patrigod.comida.entity.dto.output.ComidaOutputDto;
import com.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.comida.entity.model.Comida;
import com.patrigod.comida.mapper.ComidaMapper;
import com.patrigod.comida.service.ServiComida;
import com.patrigod.evento.entity.dto.output.EventoOutputDto;
import com.patrigod.evento.entity.model.Evento;
import com.patrigod.evento.mapper.EventoMapper;
import com.patrigod.evento.service.ServiEvento;
import com.patrigod.monumento.entity.dto.output.MonumentoOutputDto;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.monumento.entity.model.Monumento;
import com.patrigod.monumento.mapper.MonumentoMapper;
import com.patrigod.monumento.service.ServiMonumento;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    //mappers
    private final MonumentoMapper monumentoMapper;
    private final EventoMapper eventoMapper;
    private final ComidaMapper comidaMapper;
    private final CityMapper cityMapper;

    //services
    private final ServiCiudad serviCiudad;
    private final ServiMonumento serviMonumento;
    private final ServiComida serviComida;
    private final ServiEvento serviEvento;

    //useCases
    private final GetAllCityUseCase getAllCityUseCase;
    private final GetCityByIdUseCase getCityByIdUseCase;
    private final GetRankingEventOfCityUseCase getRankingEventOfCityUseCase;
    private final GetRankingMonumentOfCityUseCase getRankingMonumentOfCityUseCase;
    private final GetRankingFoodOfCityUseCase getRankingFoodOfCityUseCase;

    

    /**
     * Give all cities.
     * @return ResponseEntity con la lista de ciudades.
     */
    @GetMapping
    public ResponseEntity<List<CityOutputDto>> findAll() {
        List<City> cities = getAllCityUseCase.getAllCity();
        return new ResponseEntity<>(cities.stream()
                .map(cityMapper::toOutputDto)
                .toList(), HttpStatus.OK);
    }

    /**
     * Search city by ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la ciudad encontrada (opcional)
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityOutputDto> findCiudad(@PathVariable @NonNull Long id) {
        City city = getCityByIdUseCase.getCityById(id);
        return new ResponseEntity<>(cityMapper.toOutputDto(city) , HttpStatus.OK);
    }

    /**
     * Obtiene los monumentos de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de monumentos
     */
    @GetMapping("/{id}/monumentos")
    public ResponseEntity<List<MonumentoJpa>> findMonumentosByCiudad(@PathVariable @NonNull Long id) {
        List<MonumentoJpa> monumentos = serviCiudad.findMonumentosByCiudad(id);
        return ResponseEntity.ok(monumentos);
    }

    /**
     * Obtiene las comidas de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de comidas
     */
    @GetMapping("/{id}/comidas")
    public ResponseEntity<List<ComidaJpa>> findComidasByCiudad(@PathVariable @NonNull Long id) {
        List<ComidaJpa> comidas = serviCiudad.findComidasByCiudad(id);
        return ResponseEntity.ok(comidas);
    }

    /**
     * Obtiene los eventos de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de eventos
     */
    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<EventoOutputDto>> findEventosByCiudad(@PathVariable @NonNull Long id) {
        List<Evento> eventos = serviCiudad.findEventosByCiudad(id);
        return ResponseEntity.ok(eventos.stream()
                .map(eventoMapper::toOutputDto)
                .toList());
    }

    /**
     * Busca un monumento concreto por su ID.
     * @param idMonumento ID del monumento
     * @return ResponseEntity con el monumento encontrado (opcional)
     */
    @GetMapping("/{id}/monumentos/{idMonumento}")
    public ResponseEntity<MonumentoOutputDto> findMonumentoByCiudad(@PathVariable @NonNull Long idMonumento){
        Monumento monumento = serviMonumento.findMonumento(idMonumento);
        return ResponseEntity.ok(monumentoMapper.toOutputDto(monumento));
    }

    /**
     * Busca una comida concreta por su ID.
     * @param idComida ID de la comida
     * @return ResponseEntity con la comida encontrada (opcional)
     */
    @GetMapping("/{id}/comidas/{idComida}")
    public ResponseEntity<ComidaOutputDto> findComidaByCiudad(@PathVariable @NonNull Long idComida){
        Comida comida = serviComida.findComida(idComida);
        return ResponseEntity.ok(comidaMapper.toOutputDto(comida));
    }

    /**
     * Busca un evento concreto por su ID.
     * @param idEvento ID del evento
     * @return ResponseEntity con el evento encontrado (opcional)
     */
    @GetMapping("/{id}/eventos/{idEvento}")
    public ResponseEntity<EventoOutputDto> findEventoByCiudad(@PathVariable @NonNull Long idEvento){
        Evento evento = serviEvento.findEvento(idEvento);
        return ResponseEntity.ok(eventoMapper.toOutputDto(evento));
    }

    /**
     * Obtiene el ranking de ciudades por puntuación promedio de artículos.
     * @return ResponseEntity con la lista de ciudades ordenadas por ranking
     */
    @GetMapping("/rank")
    public ResponseEntity<List<CityJpa>> ranking() {
        List<CityJpa> ranking = serviCiudad.obtenerRankingDeCiudades();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación media de monumentos.
     * @return ResponseEntity con la lista de DTOs de ranking de monumentos
     */
    @GetMapping("/rank/monumento")
    public ResponseEntity<List<RankingArticleDto>> rankingMonumento() {
        List<RankingArticleDto> ranking = getRankingMonumentOfCityUseCase.findRankingMonumentOfCity();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación media de comidas.
     * @return ResponseEntity con la lista de DTOs de ranking de comidas
     */
    @GetMapping("/rank/comida")
    public ResponseEntity<List<RankingArticleDto>> rankingComida() {
        List<RankingArticleDto> ranking = getRankingFoodOfCityUseCase.findRankingFoodOfCity();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación media de eventos.
     * @return ResponseEntity con la lista de DTOs de ranking de eventos
     */
    @GetMapping("/rank/evento")
    public ResponseEntity<List<RankingArticleDto>> rankingEvento() {
        List<RankingArticleDto> ranking = getRankingEventOfCityUseCase.findRankingEventOfCity();
        return ResponseEntity.ok(ranking);
    }
}
