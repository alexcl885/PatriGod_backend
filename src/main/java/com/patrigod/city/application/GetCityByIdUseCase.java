package com.patrigod.city.application;

import com.patrigod.city.domain.entity.City;

public interface GetCityByIdUseCase {
    City getCityById(Long id);
}
