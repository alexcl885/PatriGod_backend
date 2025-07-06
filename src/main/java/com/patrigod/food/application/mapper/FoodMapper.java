package com.patrigod.food.application.mapper;

import com.patrigod.food.domain.entity.Food;
import org.mapstruct.Mapper;

import com.patrigod.food.infrastructure.controller.dto.input.FoodInputDto;
import com.patrigod.food.infrastructure.controller.dto.output.FoodOutputDto;
import com.patrigod.food.infrastructure.repository.jpa.entity.FoodJpa;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    Food toModel(FoodJpa foodJpa);

    FoodJpa toEntity(Food food);

    FoodOutputDto toOutputDto(Food food);

    Food toInputDto(FoodInputDto foodInputDto);

}
