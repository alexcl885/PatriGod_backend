package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetAllCityUseCase;
import com.patrigod.city.domain.entity.City;
import com.patrigod.city.domain.repository.GetAllCityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCityUseCaseImpl implements GetAllCityUseCase {

    private final GetAllCityRepository getAllCityRepository;

    @Override
    public List<City> getAllCity() {
        return getAllCityRepository.getAllCity();
    }
}
