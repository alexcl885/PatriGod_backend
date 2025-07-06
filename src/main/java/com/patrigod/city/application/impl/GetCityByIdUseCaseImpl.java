package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.domain.entity.City;
import com.patrigod.city.domain.repository.GetCityByIdRepository;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCityByIdUseCaseImpl implements GetCityByIdUseCase {

    private final GetCityByIdRepository getCityByIdRepository;

    @Override
    public City getCityById(Long id) {
        return getCityByIdRepository.getCityById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.CITY_NOT_FOUND_BY_ID_EXCEPTION + id));
    }
}
