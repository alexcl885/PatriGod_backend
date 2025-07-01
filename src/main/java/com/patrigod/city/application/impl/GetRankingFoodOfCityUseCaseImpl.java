package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetRankingFoodOfCityUseCase;
import com.patrigod.city.domain.repository.GetRankingFoodOfCityRepository;
import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRankingFoodOfCityUseCaseImpl implements GetRankingFoodOfCityUseCase {

    private final GetRankingFoodOfCityRepository getRankingFoodOfCityRepository;

    @Override
    public List<RankingArticleDto> findRankingFoodOfCity() {
        return getRankingFoodOfCityRepository.findRankingFoodOfCity();

    }
}
