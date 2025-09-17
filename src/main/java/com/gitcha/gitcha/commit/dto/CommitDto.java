package com.gitcha.gitcha.commit.dto;

import java.time.LocalDateTime;

public record CommitDto(Long id, String message, LocalDateTime committedAt, int points, Long userId) {}
