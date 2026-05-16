package com.hacakthon.team1.reviewrequest.application.dto.response;

import java.time.LocalDateTime;

public record ReviewRequestSummaryResponse(
        Long reviewId,
        Long projectId,
        String title,
        Long requesterId,
        LocalDateTime createdAt
) {}
