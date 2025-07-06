package com.patrigod.user.infrastructure.repository.impl;

import com.patrigod.user.application.mapper.UserMapper;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.repository.CreateUserRepository;
import com.patrigod.user.infrastructure.repository.jpa.UserRepositoryJpa;
import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateUserRepositoryImpl implements CreateUserRepository {

    private final UserMapper userMapper;

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public User createUser(User user) {
        UserJpa userJpa = userMapper.toUserJpa(user);
        UserJpa createdUser = userRepositoryJpa.save(userJpa);
        return userMapper.toUser(createdUser);
    }
}
