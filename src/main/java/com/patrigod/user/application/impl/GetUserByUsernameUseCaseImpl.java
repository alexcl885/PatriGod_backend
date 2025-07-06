package com.patrigod.user.application.impl;

import com.patrigod.user.application.GetUserByUsernameUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.repository.GetUserByUsernameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserByUsernameUseCaseImpl implements GetUserByUsernameUseCase {

    private final GetUserByUsernameRepository getUserByUsernameRepository;

    @Override
    public List<User> getUserByUsername(String username) {
        return getUserByUsernameRepository.getUserByUsername(username);
    }
}
