package com.patrigod.patrigod.comida.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.patrigod.patrigod.comida.entity.dto.input.ComidaInputDto;
import com.patrigod.patrigod.comida.entity.dto.output.ComidaOutputDto;
import com.patrigod.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.patrigod.comida.entity.model.Comida;

@Mapper(componentModel = "spring")
public interface ComidaMapper {
    Comida toModel(ComidaJpa comidaJpa);
    ComidaJpa toEntity(Comida comida);
    ComidaOutputDto toOutputDto(Comida comida);
     // Ignorar el ID al mapear desde ComidaInputDto
    Comida toInputDto(ComidaInputDto comidaInputDto);
}
