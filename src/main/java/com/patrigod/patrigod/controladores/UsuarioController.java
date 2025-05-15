package com.patrigod.patrigod.controladores;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.TipoUsuario;
import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.servicios.ServiUsuario;

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
        return ResponseEntity.ok(serviUsuario.save(u));
    }

}
