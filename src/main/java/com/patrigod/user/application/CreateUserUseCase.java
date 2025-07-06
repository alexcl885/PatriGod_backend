package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
