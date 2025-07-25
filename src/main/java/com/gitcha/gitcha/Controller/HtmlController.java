package com.gitcha.gitcha.Controller; // ← 네 패키지 구조에 맞게 수정

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HtmlController {

    @GetMapping("/external")
    @ResponseBody
    public ResponseEntity<String> serveExternalHtml() throws IOException {
        Path path = Paths.get("/Users/yourname/Downloads/custom-html/index.html"); // 여기도 경로 수정
        String content = Files.readString(path);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(content);
    }
}