package com.patrigod.patrigod.evento.mapper;

import org.mapstruct.Mapper;

import com.patrigod.patrigod.evento.entity.dto.input.EventoInputDto;
import com.patrigod.patrigod.evento.entity.dto.output.EventoOutputDto;
import com.patrigod.patrigod.evento.entity.entidad.EventoJpa;
import com.patrigod.patrigod.evento.entity.model.Evento;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    Evento toModel(EventoJpa eventoJpa);
    EventoJpa toEntity(Evento evento);
    EventoOutputDto toOutputDto(Evento evento);
    Evento toInputDto(EventoInputDto eventoInputDto);
}
