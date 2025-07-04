package com.patrigod.rating.application.impl;

import com.patrigod.rating.application.GetAllRatingUseCase;
import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.domain.repository.GetAllRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllRatingUseCaseImpl implements GetAllRatingUseCase {

    private final GetAllRatingRepository getAllRatingRepository;

    @Override
    public List<Rating> getAllRating() {
        return getAllRatingRepository.getAllRating();
    }
}
