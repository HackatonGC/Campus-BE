package com.hacakthon.team1.bookmark.application.dto.response;

import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.entity.ProjectType;

import java.time.LocalDateTime;
import java.util.List;

public record BookmarkResponse(
        Long bookmarkId,
        Long projectId,
        String title,
        String summary,
        List<String> techStacks,
        ProjectStatus status,
        ProjectType projectType,
        String thumbnailUrl,
        int viewCount,
        int likeCount,
        String authorName,
        LocalDateTime createdAt
) {}
