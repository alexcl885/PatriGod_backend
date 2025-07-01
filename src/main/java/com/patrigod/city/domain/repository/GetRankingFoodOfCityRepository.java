package com.patrigod.city.domain.repository;

import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;

import java.util.List;

public interface GetRankingFoodOfCityRepository {
    List<RankingArticleDto> findRankingFoodOfCity();
}
