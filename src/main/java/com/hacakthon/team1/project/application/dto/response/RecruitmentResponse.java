package com.hacakthon.team1.project.application.dto.response;

public record RecruitmentResponse(
        Long id,
        String role,
        int count,
        String description
) {}
