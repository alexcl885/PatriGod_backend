package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

import java.util.Optional;

public interface ChangeEmailOrUsernameUseCase {
    User changeEmailOrUsername(Long id, String newUsername, String newEmail);
}
