package com.patrigod.city.domain.repository;

import com.patrigod.city.infraestructure.controller.dto.output.RankingCityDto;

import java.util.List;

public interface GetRankingByCityRepository {
    List<RankingCityDto> getRankingByCity();
}
