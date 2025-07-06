package com.patrigod.user.domain.repository;

import com.patrigod.user.domain.entity.User;

public interface CreateUserRepository {
    User createUser(User user);
}
