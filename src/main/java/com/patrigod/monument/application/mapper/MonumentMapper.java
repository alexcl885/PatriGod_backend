package com.patrigod.monument.application.mapper;

import com.patrigod.monument.domain.entity.Monument;
import org.mapstruct.Mapper;

import com.patrigod.monument.infrastructure.controller.dto.input.MonumentoInputDto;
import com.patrigod.monument.infrastructure.controller.dto.output.MonumentoOutputDto;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;

@Mapper(componentModel = "spring")
public interface MonumentMapper {

    Monument toMonument(MonumentJpa monumentJpa);

    MonumentJpa toMonumentJpa(Monument monument);

    MonumentoOutputDto toMonumentOutputDto(Monument monument);

    Monument monumentInputDtoToMonument(MonumentoInputDto monumentoInputDto);
    
}
