package com.hacakthon.team1.qna.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateQnaAnswerRequest(
        @NotBlank(message = "답변 내용을 입력해주세요.")
        String content
) {}
