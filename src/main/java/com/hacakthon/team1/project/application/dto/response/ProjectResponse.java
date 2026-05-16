package com.hacakthon.team1.project.application.dto.response;

import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.entity.ProjectType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProjectResponse(
        Long id,
        String shareToken,
        String title,
        String summary,
        String description,
        List<String> techStacks,
        ProjectStatus status,
        ProjectType projectType,
        String thumbnailUrl,
        String githubUrl,
        String deployUrl,
        String figmaUrl,
        String notionUrl,
        // 모집 관련
        String expectedDuration,
        String progressMethod,
        LocalDate recruitmentDeadline,
        String recruitmentMessage,
        // 완료 관련
        String projectDuration,
        String myRole,
        String mainFeatures,
        List<String> demoImages,
        String hardships,
        String learnings,
        String messageToJuniors,
        // 통계
        int viewCount,
        int likeCount,
        long commentCount,
        long totalApplicationCount,
        String authorName,
        String authorSchool,
        List<RecruitmentResponse> recruitments,
        LocalDateTime createdAt
) {}
