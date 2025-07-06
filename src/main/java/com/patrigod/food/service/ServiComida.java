package com.patrigod.food.service;

import java.util.List;

import com.patrigod.food.domain.entity.Food;
import org.springframework.stereotype.Service;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import com.patrigod.food.infrastructure.repository.jpa.entity.FoodJpa;
import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.infrastructure.repository.jpa.FoodRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Servicio para la gestión de comidas.
 * Proporciona métodos para consultar, guardar y eliminar comidas en la base de datos.
 */
@Service
@RequiredArgsConstructor
public class ServiComida {

    private final FoodMapper foodMapper;
    
    private final FoodRepositoryJpa foodRepositoryJpa;
    private final CityRepositoryJpa cityRepositoryJpa;

    /**
     * Obtiene todas las comidas de la base de datos.
     * @return lista de comidas en modelo de dominio
     */
    public List<Food> findAll(){
        System.err.println(foodRepositoryJpa.findAll());
        return foodRepositoryJpa.findAll().stream()
                .map(foodMapper::toModel)
                .toList();
    }

    /**
     * Busca y devuelve una comida por su identificador.
     * @param id identificador de la comida
     * @return comida encontrada en modelo de dominio
     * @throws RuntimeException si no se encuentra la comida
     */
    public Food findComida(Long id){
        FoodJpa comida = foodRepositoryJpa.findById(id).orElseThrow(() -> new RuntimeException("Comida no encontrada con id: " + id));
        return foodMapper.toModel(comida);
    }

    /**
     * Guarda una nueva comida en la base de datos.
     * @param food objeto comida en modelo de dominio
     * @return comida guardada en modelo de dominio
     */
    public Food saveComida(Long idCiudad, Food food){
        CityJpa ciudad = cityRepositoryJpa.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " ));
        FoodJpa foodJpa = foodMapper.toEntity(food);
        foodJpa.setCity(ciudad);
        return foodMapper.toModel(foodRepositoryJpa.save(foodJpa));
    }

    /**
     * Elimina una comida por su identificador.
     * @param id identificador de la comida a eliminar
     * @throws RuntimeException si no se encuentra la comida
     */
    @Transactional
    public Food deleteComidaById(Long id){
        FoodJpa comida = foodRepositoryJpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Comida no encontrada con id: " + id));
        foodRepositoryJpa.delete(comida);
        return foodMapper.toModel(comida);
    }

}
