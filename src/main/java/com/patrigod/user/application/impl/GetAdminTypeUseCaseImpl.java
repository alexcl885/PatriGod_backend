package com.patrigod.user.application.impl;

import com.patrigod.user.application.GetAdminTypeUseCase;
import com.patrigod.user.application.GetAllUserUseCase;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.enums.TypeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAdminTypeUseCaseImpl implements GetAdminTypeUseCase {

    private final GetAllUserUseCase getAllUserUseCase;

    @Override
    public List<User> getAdminType() {
        return getAllUserUseCase.getAllUser().stream()
                .filter(user -> user.getType() == TypeUser.ADMINISTRADOR)
                .toList();
    }
}
