package com.patrigod.user.application.impl;

import com.patrigod.shared.exception.type.ConflictException;
import com.patrigod.user.application.ChangeEmailOrUsernameUseCase;
import com.patrigod.user.application.CreateUserUseCase;
import com.patrigod.user.application.GetUserByIdUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.infrastructure.repository.jpa.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChangeEmailOrUsernameUseCaseImpl implements ChangeEmailOrUsernameUseCase {

    // TODO cambiar que el repository no este aqui y crear dos repositorios para verificar si existen ya con esos email o username

    private final UserRepositoryJpa userRepositoryJpa;

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;

    @Override
    public User changeEmailOrUsername(Long id, String newUsername, String newEmail) {
        User user = getUserByIdUseCase.getUserById(id);

        if (newUsername != null && !newUsername.isBlank() && !newUsername.equals(user.getUsername())) {
            boolean usernameExists = userRepositoryJpa.existsByUsernameAndIdNot(newUsername, id);
            if (usernameExists) {
                throw new ConflictException("Username is already in use.");
            }
            user.setUsername(newUsername);
        }

        if (newEmail != null && !newEmail.isBlank() && !newEmail.equals(user.getEmail())) {
            boolean emailExists = userRepositoryJpa.existsByEmailAndIdNot(newEmail, id);
            if (emailExists) {
                throw new ConflictException("Email is already in use.");
            }
            user.setEmail(newEmail);
        }

        return createUserUseCase.createUser(user);
    }

}
