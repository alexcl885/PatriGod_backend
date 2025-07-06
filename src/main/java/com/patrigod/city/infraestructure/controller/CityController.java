package com.patrigod.city.infraestructure.controller;

import java.util.List;

import com.patrigod.city.application.*;
import com.patrigod.city.domain.entity.City;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.infraestructure.controller.dto.output.EventOutputDto;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.monument.application.GetAllMonumentUseCase;
import com.patrigod.monument.application.GetMonumentByIdUseCase;
import com.patrigod.monument.domain.entity.Monument;
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
import com.patrigod.food.infrastructure.controller.dto.output.FoodOutputDto;
import com.patrigod.food.infrastructure.repository.jpa.entity.FoodJpa;
import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.service.ServiComida;
import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.event.service.ServiEvento;
import com.patrigod.monument.infrastructure.controller.dto.output.MonumentoOutputDto;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;
import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.service.ServiMonumento;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    //mappers
    private final MonumentMapper monumentMapper;
    private final EventMapper eventMapper;
    private final FoodMapper foodMapper;
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

    private final GetMonumentByIdUseCase getMonumentByIdUseCase;
    private final GetAllMonumentUseCase getAllMonumentUseCase;

    

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
    public ResponseEntity<List<MonumentJpa>> findMonumentsOfCity(@PathVariable @NonNull Long id) {
        List<MonumentJpa> monumentos = serviCiudad.findMonumentosByCiudad(id);
        return ResponseEntity.ok(monumentos);
    }

    /**
     * Obtiene las comidas de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de comidas
     */
    @GetMapping("/{id}/comidas")
    public ResponseEntity<List<FoodJpa>> findFoodsByCity(@PathVariable @NonNull Long id) {
        List<FoodJpa> comidas = serviCiudad.findComidasByCiudad(id);
        return ResponseEntity.ok(comidas);
    }

    /**
     * Obtiene los eventos de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de eventos
     */
    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<EventOutputDto>> findEventsOfCity(@PathVariable @NonNull Long id) {
        List<Event> events = serviCiudad.findEventosByCiudad(id);
        return ResponseEntity.ok(events.stream()
                .map(eventMapper::toOutputDto)
                .toList());
    }

    /**
     * Busca un monumento concreto por su ID.
     * @param idMonument ID del monumento
     * @return ResponseEntity con el monumento encontrado (opcional)
     */
    @GetMapping("/{id}/monuments/{idMonument}")
    public ResponseEntity<MonumentoOutputDto> findMonumentOfCityById(@PathVariable @NonNull Long idMonument){
        Monument monument = getMonumentByIdUseCase.getMonumentById(idMonument);
        return ResponseEntity.ok(monumentMapper.toMonumentOutputDto(monument));
    }

    /**
     * Busca una comida concreta por su ID.
     * @param idFood ID de la comida
     * @return ResponseEntity con la comida encontrada (opcional)
     */
    @GetMapping("/{id}/foods/{idFood}")
    public ResponseEntity<FoodOutputDto> findFoodOfCityById(@PathVariable @NonNull Long idFood){
        Food food = serviComida.findComida(idFood);
        return ResponseEntity.ok(foodMapper.toOutputDto(food));
    }

    /**
     * Busca un evento concreto por su ID.
     * @param idEvent ID del evento
     * @return ResponseEntity con el evento encontrado (opcional)
     */
    @GetMapping("/{id}/eventos/{idEvent}")
    public ResponseEntity<EventOutputDto> findEventoByCiudad(@PathVariable @NonNull Long idEvent){
        Event event = serviEvento.findEvento(idEvent);
        return ResponseEntity.ok(eventMapper.toOutputDto(event));
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
