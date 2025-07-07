package com.patrigod.food.application.impl;

import com.patrigod.food.application.DeleteFoodByIdUseCase;
import com.patrigod.food.application.GetFoodByIdUseCase;
import com.patrigod.food.domain.repository.DeleteFoodByIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFoodByIdUseCaseImpl implements DeleteFoodByIdUseCase {

    private final DeleteFoodByIdRepository deleteFoodByIdRepository;

    private final GetFoodByIdUseCase getFoodByIdUseCase;


    @Override
    public void deleteFoodById(Long id) {

        getFoodByIdUseCase.getFoodById(id);

        deleteFoodByIdRepository.deleteFoodById(id);

    }
}
