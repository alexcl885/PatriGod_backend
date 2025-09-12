package com.patrigod.user.application.impl;

import java.util.List;
import com.patrigod.exception.messages.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;
import com.patrigod.user.infrastructure.repository.jpa.UserRepositoryJpa;

@Service
@Primary
@RequiredArgsConstructor
public class DetailUserUseCaseImpl implements UserDetailsService {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserJpa> lu = userRepositoryJpa.findByUsername(username);
        if (!lu.isEmpty()) {
                return User.builder()
                        .username(lu.getFirst().getUsername())
                        .password(lu.getFirst().getPassword())
                        .disabled(!lu.getFirst().isActive())
                        .roles(lu.getFirst().getType().name())
                        .build();
        } else {
            throw new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND_ERROR);
        }         
    }

    
}