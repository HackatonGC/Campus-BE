package com.hacakthon.team1.qna.application.dto.response;

import java.time.LocalDateTime;

public record QnaAnswerResponse(
        Long id,
        Long qnaId,
        Long authorId,
        String authorName,
        String authorSchool,
        String content,
        boolean accepted,
        int likeCount,
        LocalDateTime createdAt
) {}
