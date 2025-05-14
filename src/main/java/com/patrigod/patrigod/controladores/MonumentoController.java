package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.servicios.ServiMonumento;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/monumento")
public class MonumentoController {
    @Autowired
    private ServiMonumento serviMonumento;

    @GetMapping
    public List<Monumento> findAll() {
        return serviMonumento.findAll();
    }

    @PostMapping
    public Monumento saveMonumento(@RequestBody Monumento monumento) {
        return serviMonumento.saveMonumento(monumento);
    }
    


}
