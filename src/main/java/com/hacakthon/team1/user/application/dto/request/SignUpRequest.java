package com.hacakthon.team1.user.application.dto.request;

public record SignUpRequest(
        String email,
        String password,
        String name,
        String studentId,
        String department,
        int grade
) {}
