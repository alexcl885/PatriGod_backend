package com.patrigod.food.application;

import com.patrigod.food.domain.entity.Food;

import java.util.List;

public interface GetAllFoodUseCase {
    List<Food> getAllFood();
}
