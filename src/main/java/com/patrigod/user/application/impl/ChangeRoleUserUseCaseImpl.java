package com.patrigod.user.application.impl;


import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.ConflictException;
import com.patrigod.user.application.ChangeRoleUserUseCase;
import com.patrigod.user.application.CreateUserUseCase;
import com.patrigod.user.application.GetUserByIdUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.infrastructure.controller.dto.input.ChangeRoleInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeRoleUserUseCaseImpl implements ChangeRoleUserUseCase {

    private final GetUserByIdUseCase getUserByIdUseCase;

    private final CreateUserUseCase createUserUseCase;

    @Override
    public User changeRoleUser(Long id, ChangeRoleInputDto body) {

        if (body == null) {
            throw new ConflictException(ErrorMessages.CHANGE_ROLE_USER_ERROR);
        }

        if (!body.getUsername().equals("admin") ){
            throw new ConflictException(ErrorMessages.CHANGE_ROLE_USER_ERROR);
        }

        User user = getUserByIdUseCase.getUserById(id);
        user.setType(body.getType());

        return createUserUseCase.createUser(user);
    }
}
