package com.patrigod.user.infrastructure.controller;

import com.patrigod.user.application.*;
import com.patrigod.user.application.mapper.UserMapper;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.infrastructure.controller.dto.input.UserInputDto;
import com.patrigod.user.infrastructure.controller.dto.output.UserOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.user.infrastructure.controller.dto.input.UserUpdateInputDto;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetLoggedUserUseCase getLoggedUserUseCase;
    private final ChangeEmailOrUsernameUseCase changeEmailOrUsernameUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;


    /**
     * @return the logged-in user
     */
    @GetMapping
    public ResponseEntity<UserOutputDto> getLoggerUser() {
        User u = getLoggedUserUseCase.getLoggedUser();
        u.setPassword("");
        UserOutputDto userOutputDto = userMapper.toUserOutputDto(u);
        return new ResponseEntity<>(userOutputDto, HttpStatus.OK);
    }

    /**
     * @param id parameter to find a user by id
     * @return a user according to the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> findUserById(@PathVariable @NonNull Long id) {
        User user = getUserByIdUseCase.getUserById(id);
        UserOutputDto userOutputDto = userMapper.toUserOutputDto(user);
        return new ResponseEntity<>(userOutputDto, HttpStatus.OK);
    }


    /**
     * @param u receives a user
     * @return a saved and registered user
     */
    @PostMapping("/register")
    public ResponseEntity<UserOutputDto> register(@RequestBody UserInputDto u) {
        User user = userMapper.userInputDtoToUser(u);
        User registerUser = createUserUseCase.createUser(user);
        UserOutputDto userOutputDto = userMapper.toUserOutputDto(registerUser);
        return new ResponseEntity<>(userOutputDto, HttpStatus.CREATED);
    }

    /**
     * Updates the user's data (excluding the password).
     * @param id ID of the user to update
     * @param dto DTO containing the new data (username, email, active, subscribed)
     * @return the updated user
     */
    @PostMapping("/{id}/change-data")
    public ResponseEntity<UserOutputDto> changeEmailOrUsername(
            @PathVariable Long id,
            @RequestBody UserUpdateInputDto dto) {
        User user = changeEmailOrUsernameUseCase.changeEmailOrUsername(
                    id,
                    dto.getUsername(),
                    dto.getEmail()
                );
        return new ResponseEntity<>(userMapper.toUserOutputDto(user), HttpStatus.CREATED);
    }

    /**
     * Changes the user's password.
     * @param id ID of the user
     * @param body JSON containing the "newPassword" field
     * @return the updated user or 404 if not found
     */
    @PostMapping("/{id}/change-password")
    public ResponseEntity<UserOutputDto> cambiarPassword(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {
        String newPassword = body.get("nuevaPassword");
        User user = changePasswordUseCase.changePassword(id,newPassword);
        UserOutputDto userOutputDto = userMapper.toUserOutputDto(user);
        return new ResponseEntity<>(userOutputDto, HttpStatus.CREATED);
    }

}
