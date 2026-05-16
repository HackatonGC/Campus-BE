package com.hacakthon.team1.project.application.dto.request;

import com.hacakthon.team1.project.domain.entity.MeetingType;
import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.entity.ProjectType;

import java.time.LocalDate;
import java.util.List;

public record UpdateProjectRequest(
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
        List<RecruitmentRequest> recruitments,
        // 완료 관련
        LocalDate startDate,
        LocalDate endDate,
        String myRole,
        String features,
        List<String> demoImages,
        String hardPart,
        String learned,
        String messageToJunior,
        List<TeamMemberRequest> teamMembers
) {}
