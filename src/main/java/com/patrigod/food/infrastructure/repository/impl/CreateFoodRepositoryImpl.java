package com.patrigod.food.infrastructure.repository.impl;

import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.CreateFoodRepository;
import com.patrigod.food.infrastructure.repository.jpa.FoodRepositoryJpa;
import com.patrigod.food.infrastructure.repository.jpa.entity.FoodJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateFoodRepositoryImpl implements CreateFoodRepository {

    private final FoodMapper foodMapper;

    private final FoodRepositoryJpa foodRepositoryJpa;

    @Override
    public Food createFood(Food food) {
        FoodJpa foodJpa = foodMapper.toFoodJpa(food);
        FoodJpa createdFood = foodRepositoryJpa.save(foodJpa);
        return foodMapper.toFood(createdFood);
    }
}
