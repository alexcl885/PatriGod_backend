package com.patrigod.event.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.domain.entity.City;
import com.patrigod.event.application.CreateEventUseCase;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.CreateEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final CreateEventRepository createEventRepository;

    private final GetCityByIdUseCase getCityByIdUseCase;

    @Override
    public Event createEvent(Event event, Long idCity) {

        City city = getCityByIdUseCase.getCityById(idCity);

        event.setCity(city);

        return createEventRepository.createEvent(event);
    }
}
