package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.servicios.ServiCiudad;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {
    @Autowired
    private ServiCiudad serviCiudad;

    @GetMapping
    public List<Ciudad> findAll() {
        return serviCiudad.findAll();
    }
    
}
