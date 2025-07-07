package com.patrigod.food.application;

import com.patrigod.food.domain.entity.Food;

public interface GetFoodByIdUseCase {
    Food getFoodById(Long id);
}
