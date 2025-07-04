package com.patrigod.rating.application.mapper;

import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.infraestructure.controller.dto.input.RatingInputDto;
import com.patrigod.rating.infraestructure.controller.dto.output.RatingOutputDto;
import com.patrigod.rating.infraestructure.repository.jpa.entity.RatingJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingJpa toRatingJpa(Rating rating);

    Rating toRating(RatingJpa ratingJpa);

    RatingOutputDto toRatingOutputDto(Rating rating);

    Rating ratingInputToRating(RatingInputDto ratingInputDto);
}
