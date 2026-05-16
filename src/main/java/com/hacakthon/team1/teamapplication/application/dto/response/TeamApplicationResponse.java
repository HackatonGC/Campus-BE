package com.hacakthon.team1.teamapplication.application.dto.response;

import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record TeamApplicationResponse(
        Long id,
        Long userId,
        String userName,
        Long projectId,
        String projectTitle,
        String projectSummary,
        List<String> projectTechStacks,
        ProjectStatus projectStatus,
        String projectAuthorName,
        String role,
        String message,
        ApplicationStatus status,
        LocalDateTime createdAt
) {}
