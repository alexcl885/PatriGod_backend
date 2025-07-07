package com.patrigod.food.infrastructure.repository.impl;

import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.GetAllFoodRepository;
import com.patrigod.food.infrastructure.repository.jpa.FoodRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class GetAllFoodRepositoryImpl implements GetAllFoodRepository {

    private final FoodMapper foodMapper;

    private final FoodRepositoryJpa foodRepositoryJpa;

    @Override
    public List<Food> getAllFood() {
        return foodRepositoryJpa.findAll()
                .stream()
                .map(foodMapper::toFood)
                .toList();
    }
}
