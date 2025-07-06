package com.patrigod.rating.infrastructure.repository.impl;

import com.patrigod.rating.application.mapper.RatingMapper;
import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.domain.repository.GetAllRatingRepository;
import com.patrigod.rating.infrastructure.repository.jpa.RatingRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetAllRatingRepositoryImpl implements GetAllRatingRepository {

    private final RatingMapper ratingMapper;

    private final RatingRepositoryJpa ratingRepositoryJpa;

    @Override
    public List<Rating> getAllRating() {
        return ratingRepositoryJpa.findAll()
                .stream()
                .map(ratingMapper::toRating)
                .toList();
    }
}
