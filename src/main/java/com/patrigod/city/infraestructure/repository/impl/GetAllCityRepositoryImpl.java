package com.patrigod.city.infraestructure.repository.impl;

import com.patrigod.city.application.mapper.CityMapper;
import com.patrigod.city.domain.entity.City;
import com.patrigod.city.domain.repository.GetAllCityRepository;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetAllCityRepositoryImpl implements GetAllCityRepository {

    private final CityMapper cityMapper;

    private final CityRepositoryJpa cityRepositoryJpa;

    @Override
    public List<City> getAllCity() {
        return cityRepositoryJpa.findAll()
                .stream()
                .map(cityMapper::toModel)
                .toList();
    }
}
