package com.gitcha.gitcha.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "<a href='/oauth2/authorization/github'>GitHub 로그인</a>";
    }

    @GetMapping("/login")
    public String login() {
        return "<a href='/oauth2/authorization/github'>GitHub 로그인</a>";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        return "안녕하세요, " + principal.getAttribute("login") + "님!";
    }
}