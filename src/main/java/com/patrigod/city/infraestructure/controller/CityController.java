package com.patrigod.city.infraestructure.controller;

import java.util.List;

import com.patrigod.city.application.*;
import com.patrigod.city.domain.entity.City;
import com.patrigod.event.application.GetEventByIdUseCase;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.infraestructure.controller.dto.output.EventOutputDto;
import com.patrigod.food.application.GetFoodByIdUseCase;
import com.patrigod.food.domain.entity.Food;
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
import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.monument.infrastructure.controller.dto.output.MonumentoOutputDto;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;
import com.patrigod.monument.application.mapper.MonumentMapper;

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

    //useCases
    private final GetAllCityUseCase getAllCityUseCase;
    private final GetCityByIdUseCase getCityByIdUseCase;
    private final GetRankingEventOfCityUseCase getRankingEventOfCityUseCase;
    private final GetRankingMonumentOfCityUseCase getRankingMonumentOfCityUseCase;
    private final GetRankingFoodOfCityUseCase getRankingFoodOfCityUseCase;

    private final GetMonumentByIdUseCase getMonumentByIdUseCase;

    private final GetFoodByIdUseCase getFoodByIdUseCase;
    private final GetEventByIdUseCase getEventByIdUseCase;



    /**
     * Retrieves all cities.
     * @return ResponseEntity with the list of cities
     */
    @GetMapping
    public ResponseEntity<List<CityOutputDto>> findAll() {
        List<City> cities = getAllCityUseCase.getAllCity();
        return new ResponseEntity<>(cities.stream()
                .map(cityMapper::toOutputDto)
                .toList(), HttpStatus.OK);
    }

    /**
     * Retrieves a city by its ID.
     * @param id the ID of the city
     * @return ResponseEntity with the found city
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityOutputDto> findCiudad(@PathVariable @NonNull Long id) {
        City city = getCityByIdUseCase.getCityById(id);
        return new ResponseEntity<>(cityMapper.toOutputDto(city) , HttpStatus.OK);
    }

    /**
     * Retrieves the monuments of a city by its ID.
     * @param id the ID of the city
     * @return ResponseEntity with the list of monuments
     */
    @GetMapping("/{id}/monuments")
    public ResponseEntity<List<MonumentJpa>> findMonumentsOfCity(@PathVariable @NonNull Long id) {
        List<MonumentJpa> monumentos = serviCiudad.findMonumentosByCiudad(id);
        return ResponseEntity.ok(monumentos);
    }

    /**
     * Retrieves the foods of a city by its ID.
     * @param id the ID of the city
     * @return ResponseEntity with the list of foods
     */
    @GetMapping("/{id}/foods")
    public ResponseEntity<List<FoodJpa>> findFoodsByCity(@PathVariable @NonNull Long id) {
        List<FoodJpa> foods = serviCiudad.findComidasByCiudad(id);
        return ResponseEntity.ok(foods);
    }

    /**
     * Retrieves the events of a city by its ID.
     * @param id the ID of the city
     * @return ResponseEntity with the list of events
     */
    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventOutputDto>> findEventsOfCity(@PathVariable @NonNull Long id) {
        List<Event> events = serviCiudad.findEventosByCiudad(id);
        return ResponseEntity.ok(events.stream()
                .map(eventMapper::toOutputDto)
                .toList());
    }

    /**
     * Retrieves a specific monument by its ID.
     * @param idMonument the ID of the monument
     * @return ResponseEntity with the found monument
     */
    @GetMapping("/{id}/monuments/{idMonument}")
    public ResponseEntity<MonumentoOutputDto> findMonumentOfCityById(@PathVariable @NonNull Long idMonument){
        Monument monument = getMonumentByIdUseCase.getMonumentById(idMonument);
        return ResponseEntity.ok(monumentMapper.toMonumentOutputDto(monument));
    }

    /**
     * Retrieves a specific food by its ID.
     * @param idFood the ID of the food
     * @return ResponseEntity with the found food
     */
    @GetMapping("/{id}/foods/{idFood}")
    public ResponseEntity<FoodOutputDto> findFoodOfCityById(@PathVariable @NonNull Long idFood){
        Food food = getFoodByIdUseCase.getFoodById(idFood);
        return ResponseEntity.ok(foodMapper.toFoodOutputDto(food));
    }

    /**
     * Retrieves a specific event by its ID.
     * @param idEvent the ID of the event
     * @return ResponseEntity with the found event
     */
    @GetMapping("/{id}/eventos/{idEvent}")
    public ResponseEntity<EventOutputDto> findEventByCityById(@PathVariable @NonNull Long idEvent){
        Event event = getEventByIdUseCase.getEventById(idEvent);
        return ResponseEntity.ok(eventMapper.toOutputDto(event));
    }

    /**
     * Retrieves the ranking of cities based on the average rating of articles.
     * @return ResponseEntity with the list of cities ordered by ranking
     */
    @GetMapping("/rank")
    public ResponseEntity<List<CityJpa>> ranking() {
        List<CityJpa> ranking = serviCiudad.obtenerRankingDeCiudades();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Retrieves the ranking of cities based on the average rating of monuments.
     * @return ResponseEntity with the list of monument ranking DTOs
     */
    @GetMapping("/rank/monumento")
    public ResponseEntity<List<RankingArticleDto>> rankingMonument() {
        List<RankingArticleDto> ranking = getRankingMonumentOfCityUseCase.findRankingMonumentOfCity();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Retrieves the ranking of cities based on the average rating of foods.
     * @return ResponseEntity with the list of food ranking DTOs
     */
    @GetMapping("/rank/comida")
    public ResponseEntity<List<RankingArticleDto>> rankingFood() {
        List<RankingArticleDto> ranking = getRankingFoodOfCityUseCase.findRankingFoodOfCity();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Retrieves the ranking of cities based on the average rating of events.
     * @return ResponseEntity with the list of event ranking DTOs
     */
    @GetMapping("/rank/evento")
    public ResponseEntity<List<RankingArticleDto>> rankingEvent() {
        List<RankingArticleDto> ranking = getRankingEventOfCityUseCase.findRankingEventOfCity();
        return ResponseEntity.ok(ranking);
    }
}
