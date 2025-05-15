package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.servicios.ServiEvento;

@RestController
@RequestMapping("/api/evento")
public class EventoController {
    
    private final ServiEvento serviEvento;

    public EventoController(ServiEvento serviEvento) {
        this.serviEvento = serviEvento;
    }


    @GetMapping
    public List<Evento> findAll() {
        return serviEvento.findAll();
    }


}
