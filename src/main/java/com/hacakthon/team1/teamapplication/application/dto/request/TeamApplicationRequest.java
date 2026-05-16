package com.hacakthon.team1.teamapplication.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeamApplicationRequest(
        @NotBlank(message = "지원할 역할을 입력해주세요.")
        String role,
        @Size(max = 500, message = "신청 메시지는 500자 이하로 입력해주세요.")
        String message
) {}
