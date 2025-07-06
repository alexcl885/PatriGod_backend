package com.patrigod.event.application.mapper;

import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.infraestructure.controller.dto.input.EventInputDto;
import com.patrigod.event.infraestructure.controller.dto.output.EventOutputDto;
import org.mapstruct.Mapper;

import com.patrigod.event.infraestructure.repository.jpa.entity.EventJpa;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toModel(EventJpa eventJpa);

    EventJpa toEntity(Event event);

    EventOutputDto toOutputDto(Event event);

    Event toInputDto(EventInputDto eventInputDto);
}
