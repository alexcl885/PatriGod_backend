package com.patrigod.patrigod.puntuacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.puntuacion.entity.entity.Puntuacion;
import com.patrigod.patrigod.puntuacion.service.ServiPuntuacion;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/puntuacion")
public class PuntuacionController {
    @Autowired
    private ServiPuntuacion serviPuntuacion;

    @GetMapping
    public ResponseEntity<List<Puntuacion>> findAll() {
        return new ResponseEntity<>(serviPuntuacion.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePuntuacion(@RequestBody Puntuacion puntuacion) {
        try {
            Puntuacion guardada = serviPuntuacion.savePuntuacion(puntuacion);
            return ResponseEntity.ok(guardada);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
