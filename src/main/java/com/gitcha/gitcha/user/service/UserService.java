package com.gitcha.gitcha.user.service;

import com.gitcha.gitcha.user.domain.User;
import com.gitcha.gitcha.user.dto.UserDto;
import com.gitcha.gitcha.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(String username, String email) {
        User user = User.builder()
                .username(username)
                .points(0)
                .build();
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User addPoints(Long id, int points) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPoints(user.getPoints() + points);
        return userRepository.save(user);
    }
}
