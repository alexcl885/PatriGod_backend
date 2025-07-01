package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetRankingEventOfCityUseCase;
import com.patrigod.city.domain.repository.GetRankingEventOfCityRepository;
import com.patrigod.city.infraestructure.controller.dto.output.RankingArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRankingEventOfCityUseCaseImpl implements GetRankingEventOfCityUseCase {

    private final GetRankingEventOfCityRepository getRankingEventOfCityRepository;

    @Override
    public List<RankingArticleDto> findRankingEventOfCity() {
        return getRankingEventOfCityRepository.findRankingEventOfCity();
    }
}
