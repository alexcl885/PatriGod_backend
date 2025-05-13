package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.repos.RepoUsuario;

@Service
public class ServiUsuario {
    @Autowired
    private RepoUsuario repoUsuario;

    public Usuario getLoggedUser(){
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
        return repoUsuario.findByUsername(authentication.getName()).get(0);
    }

    public List<Usuario> findAll() {
        return repoUsuario.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return repoUsuario.findById(id);
    }
    public List<Usuario> findByName(String name) {
        return  repoUsuario.findByUsername(name);
    }

    public Usuario save(Usuario usuario) {
        // usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        // lo hace Spring por nosotros
        return repoUsuario.save(usuario);
    }    

    public void delete(Usuario usuario) {
        repoUsuario.delete(usuario);
    }

    public Optional<Usuario> update(Long id, Usuario usuario) {
        return repoUsuario.findById(id).map(existing -> {

            existing.setUsername(usuario.getUsername());
            existing.setEmail(usuario.getEmail());
            existing.setActivo(usuario.isActivo());
            existing.setPassword(usuario.getPassword());
            
            return repoUsuario.save(existing);
        });
    }
    public boolean cambiarEstado(Long id, boolean activo) {
        Optional<Usuario> optionalUsuario = repoUsuario.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setActivo(activo);
            repoUsuario.save(usuario);
            return true;
        } else {
            return false;
        }
    }
    
    


}
