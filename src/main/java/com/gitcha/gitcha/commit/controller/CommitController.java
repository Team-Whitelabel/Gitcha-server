package com.gitcha.gitcha.commit.controller;

import com.gitcha.gitcha.commit.dto.CommitDto;
import com.gitcha.gitcha.commit.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commits")
@RequiredArgsConstructor
public class CommitController {

    private final CommitService commitService;

    @PostMapping
    public CommitDto addCommit(@RequestParam Long userId,
                               @RequestParam String message,
                               @RequestParam int points) {
        return commitService.addCommit(userId, message, points);
    }

    @GetMapping("/user/{userId}")
    public List<CommitDto> getCommitsByUser(@PathVariable Long userId) {
        return commitService.getCommitsByUser(userId);
    }
}
