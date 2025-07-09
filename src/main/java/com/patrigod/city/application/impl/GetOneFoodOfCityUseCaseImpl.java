package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetOneFoodByCityUseCase;
import com.patrigod.food.application.GetFoodByIdUseCase;
import com.patrigod.food.domain.entity.Food;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOneFoodOfCityUseCaseImpl implements GetOneFoodByCityUseCase {

    private final GetFoodByIdUseCase getFoodByIdUseCase;

    @Override
    public Food getOneFoodByCity(Long idCity, Long idFood) {

        Food food = getFoodByIdUseCase.getFoodById(idFood);

        if (!food.getCity().getId().equals(idCity)){
            throw new ConflictException(ErrorMessages.CITY_NOT_EQUALS);
        }

        return food;
    }
}
