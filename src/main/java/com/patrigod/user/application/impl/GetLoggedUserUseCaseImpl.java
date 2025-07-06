package com.patrigod.user.application.impl;

import com.patrigod.user.application.GetLoggedUserUseCase;
import com.patrigod.user.application.GetUserByUsernameUseCase;
import com.patrigod.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLoggedUserUseCaseImpl implements GetLoggedUserUseCase {

    private final GetUserByUsernameUseCase getUserByUsernameUseCase;

    @Override
    public User getLoggedUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return getUserByUsernameUseCase.
                getUserByUsername(authentication.getName()).getFirst();
    }
}
