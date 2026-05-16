package com.hacakthon.team1.comment.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
        @NotBlank(message = "댓글 내용을 입력해주세요.")
        String content,
        Long parentId
) {}
