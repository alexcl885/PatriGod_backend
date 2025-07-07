package com.patrigod.food.infrastructure.repository.impl;

import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.GetFoodByIdRepository;
import com.patrigod.food.infrastructure.repository.jpa.FoodRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class GetFoodByIdRepositoryImpl implements GetFoodByIdRepository {

    private final FoodMapper foodMapper;

    private final FoodRepositoryJpa foodRepositoryJpa;

    @Override
    public Optional<Food> getFoodById(Long id) {
        return foodRepositoryJpa.findById(id).map(foodMapper::toFood);
    }
}
