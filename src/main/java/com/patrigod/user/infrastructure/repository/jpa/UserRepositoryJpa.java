package com.patrigod.user.infrastructure.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserJpa, Long> {
    List<UserJpa> findByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
