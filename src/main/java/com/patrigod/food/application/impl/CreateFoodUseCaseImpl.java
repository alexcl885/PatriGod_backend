package com.patrigod.food.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.domain.entity.City;
import com.patrigod.food.application.CreateFoodUseCase;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.CreateFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFoodUseCaseImpl implements CreateFoodUseCase {

    private final CreateFoodRepository createFoodRepository;

    private final GetCityByIdUseCase getCityByIdUseCase;

    @Override
    public Food createFood(Food food, Long idCity) {

        City city = getCityByIdUseCase.getCityById(idCity);

        food.setCity(city);

        return createFoodRepository.createFood(food);
    }
}
