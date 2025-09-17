package com.gitcha.gitcha.oauth.service;

import com.gitcha.gitcha.user.domain.User;
import com.gitcha.gitcha.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String githubId = oAuth2User.getAttribute("id").toString();
        String name = oAuth2User.getAttribute("login");

        Optional<User> userOptional = userRepository.findByGithubId(githubId);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = User.builder()
                    .githubId(githubId)
                    .username(name)
                    .points(0)
                    .build();
            userRepository.save(user);
        }

        return oAuth2User;
    }
}
