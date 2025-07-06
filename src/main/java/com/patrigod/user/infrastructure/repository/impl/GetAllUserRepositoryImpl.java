package com.patrigod.user.infrastructure.repository.impl;

import com.patrigod.user.application.mapper.UserMapper;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.repository.GetAllUserRepository;
import com.patrigod.user.infrastructure.repository.jpa.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetAllUserRepositoryImpl  implements GetAllUserRepository {

    private final UserMapper userMapper;

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public List<User> getAllUser() {
        return userRepositoryJpa.findAll()
                .stream()
                .map(userMapper::toUser)
                .toList();
    }
}
