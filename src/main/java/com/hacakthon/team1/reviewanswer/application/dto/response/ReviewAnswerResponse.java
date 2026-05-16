package com.hacakthon.team1.reviewanswer.application.dto.response;

import java.time.LocalDateTime;

public record ReviewAnswerResponse(
        Long answerId,
        Long reviewRequestId,
        Long authorId,
        String authorName,
        String content,
        LocalDateTime createdAt
) {}
