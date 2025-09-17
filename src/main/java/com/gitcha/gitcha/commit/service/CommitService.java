package com.gitcha.gitcha.commit.service;

import com.gitcha.gitcha.commit.domain.Commit;
import com.gitcha.gitcha.commit.repository.CommitRepository;
import com.gitcha.gitcha.user.domain.User;
import com.gitcha.gitcha.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommitService {

    private final CommitRepository commitRepository;
    private final UserRepository userRepository;

    public Commit createCommit(Long userId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Commit commit = Commit.builder()
                .user(user)
                .message(message)
                .build();
        return commitRepository.save(commit);
    }

    public List<Commit> getCommitsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return commitRepository.findAllByUser(user);
    }
}
