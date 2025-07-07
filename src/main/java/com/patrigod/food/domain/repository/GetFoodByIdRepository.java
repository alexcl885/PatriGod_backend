package com.patrigod.food.domain.repository;

import com.patrigod.food.domain.entity.Food;

import java.util.Optional;

public interface GetFoodByIdRepository {
    Optional<Food> getFoodById(Long id);
}
