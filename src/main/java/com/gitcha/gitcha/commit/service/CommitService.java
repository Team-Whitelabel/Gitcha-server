package com.gitcha.gitcha.commit.service;

import com.gitcha.gitcha.commit.domain.Commit;
import com.gitcha.gitcha.commit.dto.CommitDto;
import com.gitcha.gitcha.commit.repository.CommitRepository;
import com.gitcha.gitcha.user.domain.User;
import com.gitcha.gitcha.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommitService {

    private final CommitRepository commitRepository;
    private final UserService userService;

    public CommitDto addCommit(Long userId, String message, int points) {
        User user = userService.findUserById(userId);
        Commit commit = Commit.builder()
                .user(user)
                .message(message)
                .points(points)
                .committedAt(LocalDateTime.now())
                .build();
        commitRepository.save(commit);
        userService.addPoints(user, points);
        return new CommitDto(commit.getId(), commit.getMessage(), commit.getCommittedAt(), commit.getPoints(), user.getId());
    }

    public List<CommitDto> getCommitsByUser(Long userId) {
        User user = userService.findUserById(userId);
        return commitRepository.findByUser(user).stream()
                .map(c -> new CommitDto(c.getId(), c.getMessage(), c.getCommittedAt(), c.getPoints(), user.getId()))
                .collect(Collectors.toList());
    }
}
