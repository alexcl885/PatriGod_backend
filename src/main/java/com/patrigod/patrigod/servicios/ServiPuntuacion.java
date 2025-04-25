package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Puntuacion;
import com.patrigod.patrigod.repos.RepoPuntuacion;

@Service
public class ServiPuntuacion {
    @Autowired
    private RepoPuntuacion repoPuntuacion;

    public List<Puntuacion> findAll(){
        System.err.println(repoPuntuacion.findAll());
        return repoPuntuacion.findAll();
    }
}
