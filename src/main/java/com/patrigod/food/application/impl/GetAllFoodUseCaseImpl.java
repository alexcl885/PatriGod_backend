package com.patrigod.food.application.impl;

import com.patrigod.food.application.GetAllFoodUseCase;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.GetAllFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllFoodUseCaseImpl implements GetAllFoodUseCase {

    private final GetAllFoodRepository getAllFoodRepository;

    @Override
    public List<Food> getAllFood() {
        return getAllFoodRepository.getAllFood();
    }
}
