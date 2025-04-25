package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Puntuacion;
import com.patrigod.patrigod.servicios.ServiPuntuacion;

@RestController
@RequestMapping("/api/puntuacion")
public class PuntuacionController {
    @Autowired
    private ServiPuntuacion serviPuntuacion;

    @GetMapping
    public List<Puntuacion> findAll() {
        return serviPuntuacion.findAll();
    }

}
