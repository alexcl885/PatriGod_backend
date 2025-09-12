package com.patrigod.user.application.impl;

import com.patrigod.exception.messages.ErrorMessages;
import com.patrigod.user.application.ChangeStateUserUseCase;
import com.patrigod.user.application.CreateUserUseCase;
import com.patrigod.user.application.GetUserByIdUseCase;
import com.patrigod.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChangeStateUserUseCaseImpl implements ChangeStateUserUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    @Override
    public User changeStateUser(Long id, boolean active) {
        User user = getUserByIdUseCase.getUserById(id);

        if ("admin".equals(user.getUsername()) && !active) {
            throw new IllegalStateException(ErrorMessages.CHANGE_STATE_ADMIN_ERROR);
        }

        user.setActive(active);
        return createUserUseCase.createUser(user);
    }
}
