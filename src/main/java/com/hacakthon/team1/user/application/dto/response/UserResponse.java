package com.hacakthon.team1.user.application.dto.response;

public record UserResponse(
        Long id,
        String email,
        String name,
        String studentId,
        String department,
        int grade
) {}
