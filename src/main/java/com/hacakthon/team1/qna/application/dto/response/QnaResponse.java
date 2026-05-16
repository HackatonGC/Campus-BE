package com.hacakthon.team1.qna.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record QnaResponse(
        Long id,
        String title,
        String content,
        List<String> tags,
        Long authorId,
        String authorName,
        String authorSchool,
        boolean solved,
        int answerCount,
        int likeCount,
        int viewCount,
        LocalDateTime createdAt
) {}
