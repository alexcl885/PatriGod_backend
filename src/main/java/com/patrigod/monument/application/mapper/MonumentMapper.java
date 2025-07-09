package com.patrigod.monument.application.mapper;

import com.patrigod.monument.domain.entity.Monument;
import org.mapstruct.Mapper;

import com.patrigod.monument.infrastructure.controller.dto.input.MonumentInputDto;
import com.patrigod.monument.infrastructure.controller.dto.output.MonumentOutputDto;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;

@Mapper(componentModel = "spring")
public interface MonumentMapper {

    Monument toMonument(MonumentJpa monumentJpa);

    MonumentJpa toMonumentJpa(Monument monument);

    MonumentOutputDto toMonumentOutputDto(Monument monument);

    Monument monumentInputDtoToMonument(MonumentInputDto monumentInputDto);
    
}
