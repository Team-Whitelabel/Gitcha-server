package com.gitcha.gitcha.user.service;

import com.gitcha.gitcha.user.domain.User;
import com.gitcha.gitcha.user.dto.UserDto;
import com.gitcha.gitcha.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto createUser(String username) {
        User user = User.builder().username(username).points(0).role("USER").build();
        userRepository.save(user);
        return new UserDto(user.getId(), user.getUsername(), user.getPoints());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getUsername(), u.getPoints()))
                .collect(Collectors.toList());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addPoints(User user, int points) {
        user.setPoints(user.getPoints() + points);
        userRepository.save(user);
    }
}
