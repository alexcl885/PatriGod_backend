package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.servicios.ServiEvento;

@RestController
@RequestMapping("/api/evento")
public class EventoController {
    @Autowired
    private ServiEvento serviEvento;

    @GetMapping
    public List<Evento> findAll() {
        return serviEvento.findAll();
    }


}
