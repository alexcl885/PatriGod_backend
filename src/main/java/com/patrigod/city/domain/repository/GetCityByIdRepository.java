package com.patrigod.city.domain.repository;

import com.patrigod.city.domain.entity.City;

import java.util.Optional;

public interface GetCityByIdRepository {
    Optional<City> getCityById(Long id);
}
