package com.hacakthon.team1.user.application.dto.response;

import java.time.LocalDateTime;

public record UserInfoResponse(
        Long id,
        String email,
        String name,
        String school,
        String department,
        LocalDateTime createdAt
) {}
