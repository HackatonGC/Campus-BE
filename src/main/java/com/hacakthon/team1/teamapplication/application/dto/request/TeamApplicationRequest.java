package com.hacakthon.team1.teamapplication.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TeamApplicationRequest(
        @NotNull(message = "지원할 포지션을 선택해주세요.")
        Long recruitmentId,
        @Size(max = 500, message = "신청 메시지는 500자 이하로 입력해주세요.")
        String message
) {}
