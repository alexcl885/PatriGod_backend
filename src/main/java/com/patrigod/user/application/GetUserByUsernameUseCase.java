package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;

import java.util.List;

public interface GetUserByUsernameUseCase {
    List<User> getUserByUsername(String username);
}
