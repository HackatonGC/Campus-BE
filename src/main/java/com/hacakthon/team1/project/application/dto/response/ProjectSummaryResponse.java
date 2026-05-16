package com.hacakthon.team1.project.application.dto.response;

import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.entity.ProjectType;

import java.util.List;

public record ProjectSummaryResponse(
        Long id,
        String title,
        String summary,
        List<String> techStacks,
        ProjectStatus status,
        ProjectType projectType,
        String thumbnailUrl,
        int viewCount,
        int likeCount,
        String authorName,
        String authorSchool
) {}
