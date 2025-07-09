package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.application.GetMonumentByCityUseCase;
import com.patrigod.monument.application.GetAllMonumentUseCase;
import com.patrigod.monument.domain.entity.Monument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMonumentByCityUseCaseImpl implements GetMonumentByCityUseCase {

    private final GetAllMonumentUseCase getAllMonumentUseCase;

    private final GetCityByIdUseCase getCityByIdUseCase;

    @Override
    public List<Monument> getMonumentByCity(Long idMonument) {

        getCityByIdUseCase.getCityById(idMonument);

        return getAllMonumentUseCase.getAllMonument().
                stream()
                .filter((monument) -> monument.getCity().getId().equals(idMonument))
                .toList();
    }
}
