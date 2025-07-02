package com.patrigod.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.patrigod.usuario.enums.TipoUsuario;
import com.patrigod.shared.exception.type.EntityNotFoundException;
import com.patrigod.usuario.entity.entity.Usuario;
import com.patrigod.usuario.repository.RepoUsuario;

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
            //que no se pueda desactivar al admin supremo
            if (usuario.getUsername().equals("admin")){
                return false;
            }
            usuario.setActivo(activo);
            repoUsuario.save(usuario);
            return true;
        } else {
            return false;
        }
    }
    /**
     * 
     * @return una lista de usuarios Tipo USUARIO
     */
    public List<Usuario> findTipoUsuario(){
        return repoUsuario.findAll().stream()
                                    .filter(user -> user.getTipo() == TipoUsuario.USUARIO)
                                    .toList();
    }

    /**
     * 
     * @return una lista de usuarios Tipo USUARIO
     */
    public List<Usuario> findTipoAdministrador(){
        return repoUsuario.findAll().stream()
                                    .filter(user -> user.getTipo() == TipoUsuario.ADMINISTRADOR)
                                    .toList();
    }

    /**
 * Actualiza el username y/o email de un usuario
 * 
 * @param id ID del usuario a actualizar
 * @param nuevoUsername Nuevo nombre de usuario (puede ser null si no se cambia)
 * @param nuevoEmail Nuevo email (puede ser null si no se cambia)
 * @return el usuario actualizado o vacío si no se encontró
 */
public Optional<Usuario> actualizarUsernameOEmail(Long id, String nuevoUsername, String nuevoEmail) {
    return repoUsuario.findById(id).map(usuario -> {

        if (nuevoUsername != null && !nuevoUsername.isBlank()) {
            boolean usernameExiste = repoUsuario.existsByUsernameAndIdNot(nuevoUsername, id);
            if (usernameExiste) {
                throw new RuntimeException("El nombre de usuario ya está en uso.");
            }
            usuario.setUsername(nuevoUsername);
        }

        if (nuevoEmail != null && !nuevoEmail.isBlank()) {
            boolean emailExiste = repoUsuario.existsByEmailAndIdNot(nuevoEmail, id);
            if (emailExiste) {
                throw new RuntimeException("El email ya está en uso.");
            }
            usuario.setEmail(nuevoEmail);
        }

        return repoUsuario.save(usuario);
    });
}

/**
     * Actualiza los datos del usuario (excepto la contraseña).
     *
     * @param id ID del usuario a actualizar
     * @param nuevoUsername Nuevo nombre de usuario (puede ser null o vacío para no cambiar)
     * @param nuevoEmail Nuevo email (puede ser null o vacío para no cambiar)
     * @return el usuario actualizado o vacío si no se encontró
     */
    public Optional<Usuario> actualizarDatosUsuario(Long id, String nuevoUsername, String nuevoEmail) {
        return repoUsuario.findById(id).map(usuario -> {
            if (nuevoUsername != null && !nuevoUsername.isBlank() && !nuevoUsername.equals(usuario.getUsername())) {
                boolean usernameExiste = repoUsuario.existsByUsernameAndIdNot(nuevoUsername, id);
                if (usernameExiste) {
                    throw new RuntimeException("El nombre de usuario ya está en uso.");
                }
                usuario.setUsername(nuevoUsername);
            }
            if (nuevoEmail != null && !nuevoEmail.isBlank() && !nuevoEmail.equals(usuario.getEmail())) {
                boolean emailExiste = repoUsuario.existsByEmailAndIdNot(nuevoEmail, id);
                if (emailExiste) {
                    throw new RuntimeException("El email ya está en uso.");
                }
                usuario.setEmail(nuevoEmail);
            }
            return repoUsuario.save(usuario);
        });
    }

    /**
     * Cambia la contraseña del usuario.
     * 
     * @param id ID del usuario
     * @param nuevaPassword Nueva contraseña ya codificada (BCrypt)
     * @return el usuario actualizado o vacío si no se encontró
     */
    public Optional<Usuario> cambiarPassword(Long id, String nuevaPassword) {
        return repoUsuario.findById(id).map(usuario -> {
            usuario.setPassword(nuevaPassword);
            return repoUsuario.save(usuario);
        });
    }
    /**
     * Cambia el rol (tipo) de un usuario.
     * 
     * @param id ID del usuario
     * @param nuevoTipo Nuevo tipo de usuario (rol) a asignar
     * @return el usuario actualizado o vacío si no se encontró
     */
    public Usuario cambiarRol(Long id, TipoUsuario nuevoTipo) {
        return repoUsuario.findById(id).map(usuario -> {
            usuario.setTipo(nuevoTipo);
            return repoUsuario.save(usuario);
        }).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    
    


}
