package com.patrigod.patrigod.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.servicios.ServiUsuario;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private ServiUsuario serviUsuario;

    @GetMapping
    public Usuario getUser() {
        Usuario u = serviUsuario.getLoggedUser();
        u.setPassword("");
        return u;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findOne(@PathVariable @NonNull Long id) {
        Optional<Usuario> oUsuario = serviUsuario.findById(id);
        return oUsuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario u) {
        Usuario loggedUser = serviUsuario.getLoggedUser();
        if (u.getId() == loggedUser.getId()) {
            if (u.getPassword()==null) u.setPassword(loggedUser.getPassword());
            if (u.getPassword().length()<=4) u.setPassword(loggedUser.getPassword());
            return ResponseEntity.ok(serviUsuario.save(u));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
