package com.patrigod.user.infrastructure.controller.dto.output;

import com.patrigod.user.domain.enums.TypeUser;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOutputDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private TypeUser type;

    private boolean active;

    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean subscribed;
}
