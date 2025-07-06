package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

public interface ChangeStateUserUseCase {
    User changeStateUser(Long id, boolean active);
}
