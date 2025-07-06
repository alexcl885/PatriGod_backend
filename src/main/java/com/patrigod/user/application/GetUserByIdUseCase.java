package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

public interface GetUserByIdUseCase {
    User getUserById(Long id);
}
