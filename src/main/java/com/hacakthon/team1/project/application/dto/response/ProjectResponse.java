package com.hacakthon.team1.project.application.dto.response;

import com.hacakthon.team1.project.domain.entity.MeetingType;
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
        String duration,
        MeetingType meetingType,
        LocalDate deadline,
        String recruitMessage,
        List<RecruitmentResponse> recruitments,
        // 완료 관련
        LocalDate startDate,
        LocalDate endDate,
        String myRole,
        String features,
        List<String> demoImages,
        String hardPart,
        String learned,
        String messageToJunior,
        List<TeamMemberResponse> teamMembers,
        // 통계
        int viewCount,
        int likeCount,
        long commentCount,
        long totalApplicationCount,
        Long authorId,
        String authorName,
        String authorSchool,
        LocalDateTime createdAt
) {}
