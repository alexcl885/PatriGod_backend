package com.patrigod.rating.infraestructure.controller;

import java.util.List;
import com.patrigod.rating.application.CreateRatingUseCase;
import com.patrigod.rating.application.GetAllRatingUseCase;
import com.patrigod.rating.application.mapper.RatingMapper;
import com.patrigod.rating.domain.entity.Rating;
import com.patrigod.rating.infraestructure.controller.dto.input.RatingInputDto;
import com.patrigod.rating.infraestructure.controller.dto.output.RatingOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rating")
public class RatingController {

    private final RatingMapper ratingMapper;

    private final CreateRatingUseCase createRatingUseCase;
    private final GetAllRatingUseCase getAllRatingUseCase;

    @GetMapping
    public ResponseEntity<List<RatingOutputDto>> findAll() {
        List<RatingOutputDto> ratings = getAllRatingUseCase.getAllRating()
                .stream()
                .map(ratingMapper::toRatingOutputDto)
                .toList();
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RatingOutputDto> savePuntuacion(@RequestBody RatingInputDto ratingInputDto) {
        Rating rating = ratingMapper.ratingInputToRating(ratingInputDto);
        Rating createdRating = createRatingUseCase.createRating(rating);
        return new ResponseEntity<>(ratingMapper.toRatingOutputDto(createdRating), HttpStatus.CREATED);
    }

}
