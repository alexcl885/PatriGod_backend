package com.patrigod.rating.application.mapper;

import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.infrastructure.controller.dto.input.RatingInputDto;
import com.patrigod.rating.infrastructure.controller.dto.output.RatingOutputDto;
import com.patrigod.rating.infrastructure.repository.jpa.entity.RatingJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MonumentMapper.class, FoodMapper.class, EventMapper.class})
public interface RatingMapper {

    @Mapping(target = "article", ignore = true)
    RatingJpa toRatingJpa(Rating rating);

    @Mapping(target = "article", ignore = true)
    Rating toRating(RatingJpa ratingJpa);

    @Mapping(target = "article", ignore = true)
    RatingOutputDto toRatingOutputDto(Rating rating);

    @Mapping(target = "article", ignore = true)
    Rating ratingInputToRating(RatingInputDto ratingInputDto);

}
