package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

public interface ChangePasswordUseCase {
    User changePassword(Long id, String newPassword);
}
