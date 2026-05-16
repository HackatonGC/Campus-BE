package com.hacakthon.team1.teamapplication.application.dto.response;

import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;

import java.time.LocalDateTime;

public record TeamApplicationResponse(
        Long id,
        Long userId,
        String userName,
        Long projectId,
        String message,
        ApplicationStatus status,
        LocalDateTime createdAt
) {}
