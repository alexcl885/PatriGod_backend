package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetOneMonumentOfCityUseCase;
import com.patrigod.monument.application.GetMonumentByIdUseCase;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOneMonumentOfCityUseCaseImpl implements GetOneMonumentOfCityUseCase {

    private final GetMonumentByIdUseCase getMonumentByIdUseCase;

    @Override
    public Monument getOneMonumentByCity(Long idCity, Long idMonument) {

        Monument monument = getMonumentByIdUseCase.getMonumentById(idMonument);

        if (!monument.getCity().getId().equals(idCity)){
            throw new ConflictException(ErrorMessages.CITY_NOT_EQUALS);
        }

        return monument;
    }
}
