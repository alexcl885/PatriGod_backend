package com.patrigod.user.infrastructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.patrigod.user.domain.enums.TypeUser;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TypeUser type;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(nullable = false)
    private boolean subscribed;
}
