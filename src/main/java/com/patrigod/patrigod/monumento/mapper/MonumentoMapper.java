package com.patrigod.patrigod.monumento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.patrigod.patrigod.monumento.entity.dto.input.MonumentoInputDto;
import com.patrigod.patrigod.monumento.entity.dto.output.MonumentoOutputDto;
import com.patrigod.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.patrigod.monumento.entity.model.Monumento;

@Mapper(componentModel = "spring")
public interface MonumentoMapper {

    Monumento toModel(MonumentoJpa monumentoJpa);
    MonumentoJpa toEntity(Monumento monumento);
    MonumentoOutputDto toOutputDto(Monumento monumento);
    @Mapping(target = "ciudad", ignore = true) // Ignorar el campo 'type' en la conversión
    Monumento toInputDto(MonumentoInputDto monumentoInputDto);
    
}
