package com.patrigod.rating.infrastructure.repository.impl;

import com.patrigod.rating.application.mapper.RatingMapper;
import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.domain.repository.CreateRatingRepository;
import com.patrigod.rating.infrastructure.repository.jpa.RatingRepositoryJpa;
import com.patrigod.rating.infrastructure.repository.jpa.entity.RatingJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CreateRatingRepositoryImpl implements CreateRatingRepository {

    private final RatingRepositoryJpa ratingRepositoryJpa;

    private final RatingMapper ratingMapper;


    @Override
    public Rating createRating(Rating rating) {
        Long userId = rating.getUser().getId();
        Long articleId = rating.getArticle().getId();

        Optional<RatingJpa> exist = ratingRepositoryJpa.findByUser_IdAndArticle_Id(userId, articleId);

        exist.ifPresent(p -> ratingRepositoryJpa.deleteById(p.getId()));

        RatingJpa newRating = ratingMapper.toRatingJpa(rating);
        RatingJpa createdRating = ratingRepositoryJpa.save(newRating);

        return ratingMapper.toRating(createdRating);
    }
}
