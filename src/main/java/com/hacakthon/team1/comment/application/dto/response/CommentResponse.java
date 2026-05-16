package com.hacakthon.team1.comment.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record CommentResponse(
        Long id,
        Long authorId,
        String authorName,
        String authorSchool,
        String content,
        Long parentId,
        int likeCount,
        LocalDateTime createdAt,
        List<CommentResponse> replies
) {}
