package com.patrigod.rating.application;

import com.patrigod.rating.domain.entity.Rating;

public interface CreateRatingUseCase {
    Rating createRating(Rating rating);
}
