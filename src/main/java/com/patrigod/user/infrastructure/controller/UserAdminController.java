package com.patrigod.user.infrastructure.controller;

import java.util.List;
import java.util.Map;
import com.patrigod.user.application.ChangeRoleUserUseCase;
import com.patrigod.user.application.ChangeStateUserUseCase;
import com.patrigod.user.application.GetAllUserUseCase;
import com.patrigod.user.application.GetUserByUsernameUseCase;
import com.patrigod.user.application.mapper.UserMapper;
import com.patrigod.user.domain.entity.User;
import com.patrigod.user.infrastructure.controller.dto.output.UserOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.patrigod.user.infrastructure.controller.dto.input.ChangeRoleInputDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserMapper userMapper;

    private final GetAllUserUseCase getAllUserUseCase;
    private final ChangeRoleUserUseCase changeRoleUserUseCase;
    private final ChangeStateUserUseCase changeStateUserUseCase;
    private final GetUserByUsernameUseCase getUserByUsernameUseCase;

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> findAllUsers() {
        List<UserOutputDto> userOutputDto = getAllUserUseCase.getAllUser()
                .stream()
                .map(userMapper::toUserOutputDto)
                .toList();
        return new ResponseEntity<>(userOutputDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<UserOutputDto> changeStateUser(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Boolean active = body.get("activo");
        User user = changeStateUserUseCase.changeStateUser(id, active);
        return new ResponseEntity<>(userMapper.toUserOutputDto(user), HttpStatus.CREATED);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<UserOutputDto>> findUsersByUsername(@PathVariable String username) {
        List<UserOutputDto> user = getUserByUsernameUseCase.getUserByUsername(username)
                .stream()
                .map(userMapper::toUserOutputDto)
                .toList();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Changes the role (type) of a user.
     * @param id ID of the user to modify
     * @param body JSON containing the "type" field (e.g., "ADMINISTRATOR" or "USER")
     * @return ResponseEntity with the updated user or 404 if not found
     */
    @PutMapping("/{id}/role")
    public ResponseEntity<UserOutputDto> changeRole(@PathVariable Long id, @RequestBody ChangeRoleInputDto body) {
        User user = changeRoleUserUseCase.changeRoleUser(id,body );
        UserOutputDto userOutputDto = userMapper.toUserOutputDto(user);
        return new ResponseEntity<>(userOutputDto, HttpStatus.CREATED);
    }
    
}
