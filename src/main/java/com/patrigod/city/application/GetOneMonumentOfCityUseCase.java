package com.patrigod.city.application;

import com.patrigod.monument.domain.entity.Monument;

import java.util.List;

public interface GetOneMonumentOfCityUseCase {
    Monument getOneMonumentByCity(Long idCity, Long idMonument);

}
