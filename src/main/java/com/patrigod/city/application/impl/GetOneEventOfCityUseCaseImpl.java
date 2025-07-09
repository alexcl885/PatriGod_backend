package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetOneEventOfCityUseCase;
import com.patrigod.event.application.GetEventByIdUseCase;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOneEventOfCityUseCaseImpl implements GetOneEventOfCityUseCase {

    private final GetEventByIdUseCase getEventByIdUseCase;

    @Override
    public Event getOneEventOfCity(Long idCity, Long idEvent) {

        Event event = getEventByIdUseCase.getEventById(idEvent);

        if (!event.getCity().getId().equals(idCity)){
            throw new ConflictException(ErrorMessages.CITY_NOT_EQUALS);
        }

        return event;
    }
}
