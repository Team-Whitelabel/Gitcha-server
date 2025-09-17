package com.gitcha.gitcha.commit.domain;

import com.gitcha.gitcha.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commits")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime committedAt;

    private int points;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
