package com.patrigod.user.application.impl;

import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.ConflictException;
import com.patrigod.user.application.CreateUserUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.enums.TypeUser;
import com.patrigod.user.domain.repository.CreateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final CreateUserRepository createUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        if (user.getPassword() == null || user.getPassword().length() <= 4) {
            throw new ConflictException(ErrorMessages.PASSWORD_ERROR);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setCreationDate(LocalDateTime.now());
        user.setType(TypeUser.USUARIO); // defect
        user.setSubscribed(false);

        return createUserRepository.createUser(user);
    }
}
