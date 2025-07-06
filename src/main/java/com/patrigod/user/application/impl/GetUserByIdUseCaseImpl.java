package com.patrigod.user.application.impl;

import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.EntityNotFoundException;
import com.patrigod.user.application.GetUserByIdUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.repository.GetUserByIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    public final GetUserByIdRepository getUserByIdRepository;

    @Override
    public User getUserById(Long id) {
        return getUserByIdRepository.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_ID_EXCEPTION + id));
    }
}
