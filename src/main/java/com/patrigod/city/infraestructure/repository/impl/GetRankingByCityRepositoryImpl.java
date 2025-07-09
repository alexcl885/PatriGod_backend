package com.patrigod.city.infraestructure.repository.impl;

import com.patrigod.city.domain.repository.GetRankingByCityRepository;
import com.patrigod.city.infraestructure.controller.dto.output.RankingCityDto;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetRankingByCityRepositoryImpl implements GetRankingByCityRepository {

    private final CityRepositoryJpa cityRepositoryJpa;

    @Override
    public List<RankingCityDto> getRankingByCity() {
        return cityRepositoryJpa.findCityRankingByAverageRating();
    }
}
