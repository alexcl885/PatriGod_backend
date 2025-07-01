package com.patrigod.ciudad.mapper;

import org.mapstruct.Mapper;

import com.patrigod.ciudad.entity.dto.input.CiudadInputDto;
import com.patrigod.ciudad.entity.dto.output.CiudadOutputDto;
import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.ciudad.entity.model.Ciudad;

@Mapper(componentModel = "spring")
public interface CiudadMapper {
    Ciudad toModel(CiudadJpa ciudad);
    CiudadJpa toEntity(Ciudad ciudad);
    CiudadOutputDto toOutputDto(Ciudad ciudad);
    CiudadInputDto toInputDto(Ciudad ciudad);
    
}
