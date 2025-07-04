package com.patrigod.rating.domain.repository;

import com.patrigod.rating.domain.entity.Rating;

public interface CreateRatingRepository {
    Rating createRating(Rating rating);
}
