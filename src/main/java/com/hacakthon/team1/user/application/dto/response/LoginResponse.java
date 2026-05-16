package com.hacakthon.team1.user.application.dto.response;

public record LoginResponse(
        String token,
        Long userId,
        String name
) {}
