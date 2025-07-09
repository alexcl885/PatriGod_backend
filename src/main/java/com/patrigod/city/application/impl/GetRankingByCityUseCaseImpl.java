package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetRankingByCityUseCase;
import com.patrigod.city.domain.entity.City;
import com.patrigod.city.domain.repository.GetRankingByCityRepository;
import com.patrigod.city.infraestructure.controller.dto.output.RankingCityDto;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetRankingByCityUseCaseImpl implements GetRankingByCityUseCase {

    private final GetRankingByCityRepository getRankingByCityRepository;
    private final CityRepositoryJpa cityRepositoryJpa;

    @Override
    public List<City> getRankingByCity() {
        List<RankingCityDto> ranking = getRankingByCityRepository.getRankingByCity();
        List<Long> cityIds = ranking.stream()
                .map(RankingCityDto::getCityId)
                .toList();

        List<CityJpa> cityJpaList = cityRepositoryJpa.findAllById(cityIds);

        List<City> rankedCities = new ArrayList<>();
        for (RankingCityDto dto : ranking) {
            Optional<CityJpa> cityJpaOpt = cityJpaList.stream()
                    .filter(cityJpa -> cityJpa.getId().equals(dto.getCityId()))
                    .findFirst();

            if (cityJpaOpt.isPresent()) {
               CityJpa cityJpa = cityJpaOpt.get();

                City city = City.builder()
                        .id(cityJpa.getId())
                        .name(cityJpa.getName())
                        .description(cityJpa.getDescription())
                        .province(cityJpa.getProvince())
                        .autonomousCommunity(cityJpa.getAutonomousCommunity())
                        .heritageDate(cityJpa.getHeritageDate())
                        .latitude(cityJpa.getLatitude())
                        .longitude(cityJpa.getLongitude())
                        .mainImage(cityJpa.getMainImage())
                        .rating(dto.getAverageRating())
                        .build();

                rankedCities.add(city);
            }
        }
        return rankedCities;
    }
}
