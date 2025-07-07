package com.patrigod.food.application.impl;

import com.patrigod.food.application.CreateFoodUseCase;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.CreateFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFoodUseCaseImpl implements CreateFoodUseCase {

    private final CreateFoodRepository createFoodRepository;

    @Override
    public Food createFood(Food food) {
        return createFoodRepository.createFood(food);
    }
}
