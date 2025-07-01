package com.patrigod.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.usuario.entity.entity.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
