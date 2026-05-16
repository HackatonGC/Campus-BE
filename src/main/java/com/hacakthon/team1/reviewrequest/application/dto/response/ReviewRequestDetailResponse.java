package com.hacakthon.team1.reviewrequest.application.dto.response;

import java.time.LocalDateTime;

public record ReviewRequestDetailResponse(
        Long reviewId,
        Long projectId,
        String title,
        String content,
        String githubUrl,
        Long requesterId,
        LocalDateTime createdAt
) {}
