package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.repos.RepoCiudad;

@Service
public class ServiCiudad {
    @Autowired
    private RepoCiudad repoCiudad;

    public List<Ciudad> findAll(){
        System.err.println(repoCiudad.findAll());
        return repoCiudad.findAll();
    }
    
}
