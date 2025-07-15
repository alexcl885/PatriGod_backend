package com.patrigod.city.application.mapper;

import com.patrigod.city.infraestructure.controller.dto.input.CityInputDto;
import org.mapstruct.Mapper;

import com.patrigod.city.infraestructure.controller.dto.output.CityOutputDto;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.city.domain.entity.City;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City toModel(CityJpa ciudad);

    CityJpa toEntity(City city);

    CityOutputDto toOutputDto(City city);

    CityInputDto toInputDto(City city);
    
}
