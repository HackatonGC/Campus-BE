package com.hacakthon.team1.teamapplication.application.dto.request;

import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusRequest(
        @NotNull(message = "변경할 상태를 입력해주세요.")
        ApplicationStatus status
) {}
