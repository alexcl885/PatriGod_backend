package com.patrigod.city.application;

import com.patrigod.food.domain.entity.Food;

import java.util.List;

public interface GetFoodByCityUseCase {
    List<Food> getFoodByCity(Long idCity);
}
