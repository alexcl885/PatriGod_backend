package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.application.GetFoodByCityUseCase;
import com.patrigod.food.application.GetAllFoodUseCase;
import com.patrigod.food.domain.entity.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFoodByCityUseCaseImpl implements GetFoodByCityUseCase {

    private final GetAllFoodUseCase getAllFoodUseCase;

    private final GetCityByIdUseCase getCityByIdUseCase;

    @Override
    public List<Food> getFoodByCity(Long idCity) {

        getCityByIdUseCase.getCityById(idCity);

        return getAllFoodUseCase.getAllFood()
                .stream()
                .filter((food) -> food.getCity().getId().equals(idCity))
                .toList();
    }
}
