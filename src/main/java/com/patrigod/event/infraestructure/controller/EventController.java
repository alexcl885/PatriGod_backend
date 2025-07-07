package com.patrigod.event.infraestructure.controller;

import java.util.List;
import com.patrigod.event.application.CreateEventUseCase;
import com.patrigod.event.application.DeleteEventByIdUseCase;
import com.patrigod.event.application.GetAllEventUseCase;
import com.patrigod.event.application.GetEventByIdUseCase;
import com.patrigod.event.infraestructure.controller.dto.input.EventInputDto;
import com.patrigod.event.infraestructure.controller.dto.output.EventOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.application.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventMapper eventMapper;

    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventByIdUseCase deleteEventByIdUseCase;
    private final GetAllEventUseCase getAllEventUseCase;
    private final GetEventByIdUseCase getEventByIdUseCase;

    /**
     * Retrieves the list of all events.
     * @return ResponseEntity containing the list of events in output DTO format.
     */
    @GetMapping
    public ResponseEntity<List<EventOutputDto>> findAllEvent() {
        List<EventOutputDto> events = getAllEventUseCase.getAllEvent().stream()
                .map(eventMapper::toOutputDto)
                .toList();
        return new ResponseEntity<>(events , HttpStatus.OK);
    }

    /**
     * Saves a new event to the database.
     * @param eventInputDto InputDto object received in the request body.
     * @return ResponseEntity containing the created event in output DTO format.
     */
    @PostMapping
    public ResponseEntity<EventOutputDto> createEvent(@RequestBody EventInputDto eventInputDto) {
        Event eventObject = eventMapper.toInputDto(eventInputDto);
        Event eventCreated = createEventUseCase.createEvent(eventObject);
        return new ResponseEntity<>(eventMapper.toOutputDto(eventCreated), HttpStatus.CREATED);
        
    }

    /**
     * Deletes an event by its ID.
     * @param id ID of the event to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) if the request is successful.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id){
        deleteEventByIdUseCase.deleteEventById(id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT);
    }
    


}
