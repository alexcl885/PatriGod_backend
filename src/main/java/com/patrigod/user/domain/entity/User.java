package com.patrigod.user.domain.entity;

import com.patrigod.user.domain.enums.TypeUser;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private TypeUser type;

    private boolean active;

    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean subscribed;
}
