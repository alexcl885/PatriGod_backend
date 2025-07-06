package com.patrigod.food.infrastructure.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.food.infrastructure.repository.jpa.entity.FoodJpa;

@Repository
public interface FoodRepositoryJpa extends JpaRepository<FoodJpa,Long> {
    List<FoodJpa> findFoodByCity(CityJpa ciudad);

}
