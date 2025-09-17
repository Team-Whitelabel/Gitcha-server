package com.gitcha.gitcha.commit.controller;

import com.gitcha.gitcha.commit.domain.Commit;
import com.gitcha.gitcha.commit.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commits")
public class CommitController {

    private final CommitService commitService;

    @PostMapping("/add")
    public Commit addCommit(@RequestParam Long userId, @RequestParam String message) {
        return commitService.createCommit(userId, message);
    }

    @GetMapping("/user/{userId}")
    public List<Commit> getCommitsByUser(@PathVariable Long userId) {
        return commitService.getCommitsByUser(userId);
    }
}
