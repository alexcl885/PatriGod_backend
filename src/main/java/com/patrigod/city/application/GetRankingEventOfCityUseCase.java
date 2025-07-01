package com.patrigod.city.application;

import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;

import java.util.List;

public interface GetRankingEventOfCityUseCase {
    List<RankingArticleDto> findRankingEventOfCity();
}
