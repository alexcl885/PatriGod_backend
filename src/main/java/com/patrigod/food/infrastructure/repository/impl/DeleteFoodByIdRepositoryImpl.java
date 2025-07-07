package com.patrigod.food.infrastructure.repository.impl;

import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.domain.repository.DeleteFoodByIdRepository;
import com.patrigod.food.infrastructure.repository.jpa.FoodRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeleteFoodByIdRepositoryImpl implements DeleteFoodByIdRepository {

    private final FoodMapper foodMapper;

    private final FoodRepositoryJpa foodRepositoryJpa;

    @Override
    public void deleteFoodById(Long id) {

        foodRepositoryJpa.deleteById(id);
    }
}
