package com.patrigod.user.domain.repository;

import com.patrigod.user.domain.entity.User;

import java.util.Optional;

public interface GetUserByIdRepository {
    Optional<User> getUserById(Long id);
}
