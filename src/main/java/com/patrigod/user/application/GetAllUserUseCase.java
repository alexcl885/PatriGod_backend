package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

import java.util.List;

public interface GetAllUserUseCase {
    List<User> getAllUser();
}
