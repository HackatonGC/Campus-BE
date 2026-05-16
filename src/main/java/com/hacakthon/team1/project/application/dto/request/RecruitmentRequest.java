package com.hacakthon.team1.project.application.dto.request;

public record RecruitmentRequest(
        String role,
        int count,
        String description
) {}
