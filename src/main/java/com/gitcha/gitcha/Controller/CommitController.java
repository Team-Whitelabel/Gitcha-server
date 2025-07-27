// src/main/java/com/gitcha/gitcha/controller/CommitController.java

package com.gitcha.gitcha.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommitController {

    @GetMapping("/commits/{username}")
    public int getUserCommitCount(@PathVariable String username) {
        int count = 0;
        int page = 1;

        try {
            while (true) {
                String apiUrl = "https://api.github.com/repos/Team-Whitelabel/Gitcha/commits?per_page=100&page=" + page;
                HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                connection.setRequestProperty("Accept", "application/vnd.github+json");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.lines().collect(Collectors.joining());
                reader.close();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response);

                if (!root.isArray() || root.size() == 0) break;

                for (JsonNode commit : root) {
                    JsonNode author = commit.get("author");
                    if (author != null && username.equals(author.get("login").asText())) {
                        count++;
                    }
                }

                page++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
