package com.hacakthon.team1.user.application.dto.response;

import java.time.LocalDateTime;

public record ActivityResponse(
        String type,
        String description,
        LocalDateTime createdAt
) {}
