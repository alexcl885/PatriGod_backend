package com.patrigod.city.infraestructure.repository.impl;

import com.patrigod.city.application.mapper.CityMapper;
import com.patrigod.city.domain.entity.City;
import com.patrigod.city.domain.repository.GetCityByIdRepository;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GetCityByIdRepositoryImpl implements GetCityByIdRepository {

    private final CityMapper cityMapper;

    private final CityRepositoryJpa cityRepositoryJpa;

    @Override
    public Optional<City> getCityById(Long id) {
        return cityRepositoryJpa.findById(id).map(cityMapper::toModel);
    }
}
