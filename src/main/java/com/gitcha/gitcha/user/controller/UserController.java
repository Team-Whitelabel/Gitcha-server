package com.gitcha.gitcha.user.controller;

import com.gitcha.gitcha.user.domain.User;
import com.gitcha.gitcha.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestParam String username, @RequestParam String email) {
        return userService.createUser(username, email);
    }

    @PostMapping("/{id}/addPoints")
    public User addPoints(@PathVariable Long id, @RequestParam int points) {
        return userService.addPoints(id, points);
    }
}
