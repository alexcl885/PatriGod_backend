package com.patrigod.patrigod.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.patrigod.modelos.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
