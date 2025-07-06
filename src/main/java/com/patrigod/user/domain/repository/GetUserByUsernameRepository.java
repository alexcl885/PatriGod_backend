package com.patrigod.user.domain.repository;

import com.patrigod.user.domain.entity.User;
import java.util.List;

public interface GetUserByUsernameRepository {
    List<User> getUserByUsername(String username);
}
