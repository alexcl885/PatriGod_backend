package com.patrigod.event.infraestructure.controller;

import java.util.List;

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
import com.patrigod.event.service.ServiEvento;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/evento")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    
    private final ServiEvento serviEvento;


    /**
     * Obtiene la lista de todos los eventos.
     * @return ResponseEntity con la lista de eventos en formato DTO de salida.
     */
    @GetMapping
    public ResponseEntity<List<EventOutputDto>> findAllEvent() {
        return new ResponseEntity<>( serviEvento.findAll().stream()
                .map(eventMapper::toOutputDto)
                .toList(), HttpStatus.OK);
    }

    /**
     * Guarda un nuevo evento en la base de datos.
     * @param evento Objeto InputDto recibido en el cuerpo de la petición.
     * @return ResponseEntity con el evento creado en formato DTO de salida.
     */
    @PostMapping
    public ResponseEntity<EventOutputDto> createEvent(@RequestBody EventInputDto evento) {
        Event eventObject = eventMapper.toInputDto(evento);
        Event eventCreated = serviEvento.saveEvento(evento.getCityId(), eventObject);
        return new ResponseEntity<>(eventMapper.toOutputDto(eventCreated), HttpStatus.CREATED);
        
    }
 
    /**
     * Elimina un evento por su ID.
     * @param id ID del evento a eliminar.
     * @return ResponseEntity con el evento eliminado en formato DTO de salida.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<EventOutputDto> deleteEvent(@PathVariable Long id){
        Event event = serviEvento.findEvento(id);
        serviEvento.deleteEventoById(id);
        return new ResponseEntity<> (eventMapper.toOutputDto(event), HttpStatus.OK);
    }
    


}
