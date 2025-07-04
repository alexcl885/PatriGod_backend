package com.patrigod.rating.domain.repository;

import com.patrigod.rating.domain.entity.Rating;

import java.util.List;

public interface GetAllRatingRepository {
    List<Rating> getAllRating();
}
