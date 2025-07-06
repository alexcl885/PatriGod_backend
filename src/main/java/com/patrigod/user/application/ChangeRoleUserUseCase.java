package com.patrigod.user.application;

import com.patrigod.user.domain.entity.User;
import com.patrigod.user.domain.enums.TypeUser;
import com.patrigod.user.infrastructure.controller.dto.input.ChangeRoleInputDto;

public interface ChangeRoleUserUseCase {
    User changeRoleUser(Long id, ChangeRoleInputDto body);
}
