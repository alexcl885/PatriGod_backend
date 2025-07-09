package com.patrigod.city.application.impl;

import com.patrigod.city.application.GetCityByIdUseCase;
import com.patrigod.city.application.GetEventByCityUseCase;
import com.patrigod.event.application.GetAllEventUseCase;
import com.patrigod.event.domain.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventByCityUseCaseImpl implements GetEventByCityUseCase {

    private final GetAllEventUseCase getAllEventUseCase;

    private final GetCityByIdUseCase getCityByIdUseCase;


    @Override
    public List<Event> getEventOfCity(Long idCity) {

        getCityByIdUseCase.getCityById(idCity);

        return getAllEventUseCase.getAllEvent()
                .stream()
                .filter((event) -> event.getCity().getId().equals(idCity))
                .toList();
    }
}
