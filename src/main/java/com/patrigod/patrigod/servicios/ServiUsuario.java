package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.repos.RepoUsuario;

@Service
public class ServiUsuario {
    
    private final RepoUsuario repoUsuario;


    public ServiUsuario(RepoUsuario repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    /**
     * 
     * @return un usuario logeado
     */
    public Usuario getLoggedUser(){
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
        return repoUsuario.findByUsername(authentication.getName()).get(0);
    }
    /**
     * 
     * @return una lista de usuarios
     */
    public List<Usuario> findAll() {
        return repoUsuario.findAll();
    }
    /**
     * 
     * @param id parametro identificador del usuario
     * @return un usuario dado un id por parametro
     */
    public Optional<Usuario> findById(Long id) {
        return repoUsuario.findById(id);
    }
    public List<Usuario> findByName(String name) {
        return  repoUsuario.findByUsername(name);
    }
    /**
     * 
     * @param usuario nuevo usuario que queremos introducir
     * @return un usuario nuevo 
     */
    public Usuario save(Usuario usuario) {
        // usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        // lo hace Spring por nosotros
        return repoUsuario.save(usuario);
    }    
    /**
     * 
     * @param usuario usuario para borrar
     */
    public void delete(Usuario usuario) {
        repoUsuario.delete(usuario);
    }
    /**
     * 
     * @param id parametro identificador del usuario
     * @param usuario objeto por el cual se va a modificar los valores
     * @return una nueva persona actualizada por sus valores
     */
    public Optional<Usuario> update(Long id, Usuario usuario) {
        return repoUsuario.findById(id).map(existing -> {

            existing.setUsername(usuario.getUsername());
            existing.setEmail(usuario.getEmail());
            existing.setActivo(usuario.isActivo());
            existing.setPassword(usuario.getPassword());
            
            return repoUsuario.save(existing);
        });
    }
    /**
     * 
     * @param id parametro identificador del usuario
     * @param activo boleano para cambiar la actividad del usuario
     * @return un boleano para cambiar el estado de una persona
     */
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
