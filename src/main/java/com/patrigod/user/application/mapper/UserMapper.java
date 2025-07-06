package com.patrigod.user.application.mapper;

import com.patrigod.user.domain.entity.User;
import com.patrigod.user.infrastructure.controller.dto.input.UserInputDto;
import com.patrigod.user.infrastructure.controller.dto.output.UserOutputDto;
import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserJpa userJpa);

    UserJpa toUserJpa(User user);

    UserOutputDto toUserOutputDto(User user);

    User userInputDtoToUser(UserInputDto userInputDto);
}
