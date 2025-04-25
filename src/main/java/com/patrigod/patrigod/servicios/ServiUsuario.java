package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.repos.RepoUsuario;

@Service
public class ServiUsuario {
    @Autowired
    private RepoUsuario repoUsuario;

    public List<Usuario> findAll(){
        System.err.println(repoUsuario.findAll());
        return repoUsuario.findAll();
    }


}
