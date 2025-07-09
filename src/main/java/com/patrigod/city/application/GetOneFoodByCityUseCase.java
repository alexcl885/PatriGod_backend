package com.patrigod.city.application;

import com.patrigod.food.domain.entity.Food;

public interface GetOneFoodByCityUseCase {
    Food getOneFoodByCity(Long idCity, Long idFood);
}
