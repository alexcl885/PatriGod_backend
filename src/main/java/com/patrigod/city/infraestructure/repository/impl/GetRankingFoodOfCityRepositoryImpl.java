package com.patrigod.city.infraestructure.repository.impl;

import com.patrigod.city.domain.repository.GetRankingFoodOfCityRepository;
import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetRankingFoodOfCityRepositoryImpl implements GetRankingFoodOfCityRepository {

    private final CityRepositoryJpa cityRepositoryJpa;

    @Override
    public List<RankingArticleDto> findRankingFoodOfCity() {
        return cityRepositoryJpa.findRankingByFood();
    }
}
