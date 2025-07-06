package com.patrigod.user.infrastructure.repository.impl;

import com.patrigod.user.application.mapper.UserMapper;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.repository.GetUserByIdRepository;
import com.patrigod.user.infrastructure.repository.jpa.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GetUserByIdRepositoryImpl implements GetUserByIdRepository {

    private final UserMapper userMapper;

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepositoryJpa.findById(id)
                .map(userMapper::toUser);
    }
}
