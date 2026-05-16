package com.hacakthon.team1.comment.application.mapper;

import com.hacakthon.team1.comment.application.dto.response.CommentResponse;
import com.hacakthon.team1.comment.domain.entity.Comment;

import java.util.List;

public class CommentMapper {

    public static CommentResponse toResponse(Comment comment, List<CommentResponse> replies) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getUser().getSchool(),
                comment.getContent(),
                comment.getParentId(),
                comment.getLikeCount(),
                comment.getCreatedAt(),
                replies
        );
    }
}
