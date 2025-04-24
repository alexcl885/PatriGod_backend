package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.servicios.ServiComida;

@RestController
@RequestMapping("/api/comida")
public class ComidaController {
    @Autowired
    private ServiComida serviComida;

    @GetMapping
    public List<Comida> findAll() {
        return serviComida.findAll();
    }


}
