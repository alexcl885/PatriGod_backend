package com.patrigod.evento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.evento.entity.dto.input.EventoInputDto;
import com.patrigod.evento.entity.dto.output.EventoOutputDto;
import com.patrigod.evento.entity.model.Evento;
import com.patrigod.evento.mapper.EventoMapper;
import com.patrigod.evento.service.ServiEvento;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoMapper eventoMapper;
    
    private final ServiEvento serviEvento;


    /**
     * Obtiene la lista de todos los eventos.
     * @return ResponseEntity con la lista de eventos en formato DTO de salida.
     */
    @GetMapping
    public ResponseEntity<List<EventoOutputDto>> findAll() {
        return new ResponseEntity<>( serviEvento.findAll().stream()
                .map(eventoMapper::toOutputDto)
                .toList(), HttpStatus.OK);
    }

    /**
     * Guarda un nuevo evento en la base de datos.
     * @param evento Objeto InputDto recibido en el cuerpo de la petición.
     * @return ResponseEntity con el evento creado en formato DTO de salida.
     */
    @PostMapping
    public ResponseEntity<EventoOutputDto> saveEvento(@RequestBody EventoInputDto evento) {        
        Evento eventoObject = eventoMapper.toInputDto(evento);
        Evento eventoCreated = serviEvento.saveEvento(evento.getIdCiudad(),eventoObject);
        return new ResponseEntity<>(eventoMapper.toOutputDto(eventoCreated), HttpStatus.CREATED);
        
    }
 
    /**
     * Elimina un evento por su ID.
     * @param id ID del evento a eliminar.
     * @return ResponseEntity con el evento eliminado en formato DTO de salida.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<EventoOutputDto>  deleteEvento(@PathVariable Long id){
        Evento evento = serviEvento.findEvento(id);
        serviEvento.deleteEventoById(id);
        return new ResponseEntity<> (eventoMapper.toOutputDto(evento), HttpStatus.OK);
    }
    


}
