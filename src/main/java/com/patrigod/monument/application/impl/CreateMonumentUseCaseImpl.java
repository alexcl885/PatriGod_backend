package com.patrigod.monument.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.domain.entity.City;
import com.patrigod.monument.application.CreateMonumentUseCase;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.CreateMonumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMonumentUseCaseImpl implements CreateMonumentUseCase {

    private final CreateMonumentRepository createMonumentRepository;

    private final GetCityByIdUseCase getCityByIdUseCase;

    @Override
    public Monument createMonument(Monument monument, Long idCity) {

        City city = getCityByIdUseCase.getCityById(idCity);

        monument.setCity(city);

        return createMonumentRepository.createMonument(monument, idCity);
    }
}
