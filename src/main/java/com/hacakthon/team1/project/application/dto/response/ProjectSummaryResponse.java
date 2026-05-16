package com.hacakthon.team1.project.application.dto.response;

import com.hacakthon.team1.project.domain.entity.MeetingType;
import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.entity.ProjectType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProjectSummaryResponse(
        Long id,
        String shareToken,
        String title,
        String summary,
        List<String> techStacks,
        ProjectStatus status,
        ProjectType projectType,
        String thumbnailUrl,
        // 모집 관련
        String duration,
        MeetingType meetingType,
        LocalDate deadline,
        // 완료 관련
        LocalDate startDate,
        LocalDate endDate,
        int viewCount,
        int likeCount,
        Long authorId,
        String authorName,
        String authorSchool,
        LocalDateTime createdAt
) {}
