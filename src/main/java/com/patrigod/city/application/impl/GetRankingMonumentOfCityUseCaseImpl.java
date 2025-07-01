package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetRankingMonumentOfCityUseCase;
import com.patrigod.city.domain.repository.GetRankingMonumentOfCityRepository;
import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRankingMonumentOfCityUseCaseImpl implements GetRankingMonumentOfCityUseCase {

    private final GetRankingMonumentOfCityRepository getRankingMonumentOfCityRepository;

    @Override
    public List<RankingArticleDto> findRankingMonumentOfCity() {
        return getRankingMonumentOfCityRepository.findRankingMonumentOfCity();
    }
}
