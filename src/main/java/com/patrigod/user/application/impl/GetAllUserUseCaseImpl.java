package com.patrigod.user.application.impl;

import com.patrigod.user.application.GetAllUserUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.repository.GetAllUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUserUseCaseImpl implements GetAllUserUseCase{

    private final GetAllUserRepository getAllUserRepository;

    @Override
    public List<User> getAllUser() {
        return getAllUserRepository.getAllUser();
    }
}
