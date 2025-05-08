package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Puntuacion;
import com.patrigod.patrigod.servicios.ServiPuntuacion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/puntuacion")
public class PuntuacionController {
    @Autowired
    private ServiPuntuacion serviPuntuacion;

    @GetMapping
    public List<Puntuacion> findAll() {
        return serviPuntuacion.findAll();
    }

    @PostMapping
    public Puntuacion savePuntuacion(@RequestBody Puntuacion puntuacion) {
        return serviPuntuacion.savePuntuacion(puntuacion);
    }
    

}
