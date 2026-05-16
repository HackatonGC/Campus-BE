package com.hacakthon.team1.reviewrequest.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReviewRequestRequest(
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        @Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
        String content,

        @Size(max = 255, message = "GitHub URL은 255자 이하로 입력해주세요.")
        String githubUrl
) {}
