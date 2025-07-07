package com.patrigod.food.application.impl;

import com.patrigod.food.application.GetFoodByIdUseCase;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.food.domain.repository.GetFoodByIdRepository;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFoodByIdUseCaseImpl implements GetFoodByIdUseCase {

    private final GetFoodByIdRepository getFoodByIdRepository;

    @Override
    public Food getFoodById(Long id) {
        return getFoodByIdRepository.getFoodById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.FOOD_NOT_FOUND_BY_ID_EXCEPTION + id));
    }
}
