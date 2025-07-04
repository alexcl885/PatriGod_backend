package com.patrigod.rating.application;

import com.patrigod.rating.domain.entity.Rating;

import java.util.List;

public interface GetAllRatingUseCase {
    List<Rating> getAllRating();
}
