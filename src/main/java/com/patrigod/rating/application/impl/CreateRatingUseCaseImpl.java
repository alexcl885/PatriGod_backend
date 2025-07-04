package com.patrigod.rating.application.impl;

import com.patrigod.rating.application.CreateRatingUseCase;
import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.domain.repository.CreateRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRatingUseCaseImpl implements CreateRatingUseCase {

    private final CreateRatingRepository createRatingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return createRatingRepository.createRating(rating);
    }
}
