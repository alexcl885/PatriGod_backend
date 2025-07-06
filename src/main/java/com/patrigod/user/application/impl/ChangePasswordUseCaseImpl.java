package com.patrigod.user.application.impl;

import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.ConflictException;
import com.patrigod.user.application.ChangePasswordUseCase;
import com.patrigod.user.application.CreateUserUseCase;
import com.patrigod.user.application.GetUserByIdUseCase;
import com.patrigod.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User changePassword(Long id, String newPassword) {

        if (newPassword == null || newPassword.length() <= 4) {
            throw new ConflictException(ErrorMessages.PASSWORD_ERROR);
        }

        String encodedPassword = passwordEncoder.encode(newPassword);

        User user = getUserByIdUseCase.getUserById(id);
        user.setPassword(encodedPassword);

        return createUserUseCase.createUser(user);
    }
}
