package com.patrigod.usuario.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.usuario.enums.TipoUsuario;
import com.patrigod.usuario.entity.dto.input.UsuarioActualizacionDTO;
import com.patrigod.usuario.entity.entity.Usuario;
import com.patrigod.usuario.service.ServiUsuario;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final PasswordEncoder passwordEncoder;
    private final ServiUsuario serviUsuario;

    UsuarioController(PasswordEncoder passwordEncoder, ServiUsuario serviUsuario) {
        this.passwordEncoder = passwordEncoder;
        this.serviUsuario = serviUsuario;
    }

    /**
     * 
     * @return el usuario logeado
     */
    @GetMapping
    public Usuario getUser() {
        Usuario u = serviUsuario.getLoggedUser();
        u.setPassword("");
        return u;
    }

    /**
     * 
     * @param id parametro para encontrar un usuario con un id
     * @return un usuario segun el id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findOne(@PathVariable @NonNull Long id) {
        Optional<Usuario> oUsuario = serviUsuario.findById(id);
        return oUsuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 
     * @param u parametro usuario para añadir un usuario
     * @return un nuevo usuario
     */
    @PostMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario u) {
        Usuario loggedUser = serviUsuario.getLoggedUser();
        if (u.getId() == loggedUser.getId()) {
            if (u.getPassword() == null)
                u.setPassword(loggedUser.getPassword());
            if (u.getPassword().length() <= 4)
                u.setPassword(loggedUser.getPassword());
            return ResponseEntity.ok(serviUsuario.save(u));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 
     * @param u recoge un usuario
     * @return un usuario guardado registrado
     */
    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario u) {
        if (u.getPassword() == null || u.getPassword().length() <= 4) {
            return ResponseEntity.badRequest().body(null);
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setActivo(true);
        u.setFechaCreacion(LocalDateTime.now());
        u.setTipo(TipoUsuario.USUARIO); // Asignación por defecto
        u.setSuscrito(false);
        return ResponseEntity.ok(serviUsuario.save(u));
    }

    /**
     * 
     * @param id identificador del usuario a actualizar
     * @param dto nuevos parametros a actualizar
     * @return una persona actualizada por su email o username
     */
    @PutMapping("/{id}/actualizar-usuario")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioActualizacionDTO dto) {
        return serviUsuario.actualizarUsernameOEmail(id, dto.getUsername(), dto.getEmail())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza los datos del usuario (excepto la contraseña).
     * @param id ID del usuario a actualizar
     * @param dto DTO con los nuevos datos (username, email, activo, suscrito)
     * @return el usuario actualizado o 404 si no existe
     */
    @PostMapping("/{id}/actualizar-datos")
    public ResponseEntity<Usuario> actualizarDatosUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioActualizacionDTO dto) {
        return serviUsuario.actualizarDatosUsuario(
                    id,
                    dto.getUsername(),
                    dto.getEmail()
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cambia la contraseña del usuario.
     * @param id ID del usuario
     * @param body JSON con el campo "nuevaPassword"
     * @return el usuario actualizado o 404 si no existe
     */
    @PostMapping("/{id}/cambiar-password")
    public ResponseEntity<Usuario> cambiarPassword(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {
        String nuevaPassword = body.get("nuevaPassword");
        if (nuevaPassword == null || nuevaPassword.length() <= 4) {
            return ResponseEntity.badRequest().build();
        }
        String encodedPassword = passwordEncoder.encode(nuevaPassword);
        return serviUsuario.cambiarPassword(id, encodedPassword)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
