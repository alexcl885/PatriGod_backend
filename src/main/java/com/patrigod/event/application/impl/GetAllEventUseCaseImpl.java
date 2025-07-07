package com.patrigod.event.application.impl;

import com.patrigod.event.application.GetAllEventUseCase;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.GetAllEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllEventUseCaseImpl implements GetAllEventUseCase {

    private final GetAllEventRepository getAllEventRepository;

    @Override
    public List<Event> getAllEvent() {
        return getAllEventRepository.getAllEvent();
    }
}
