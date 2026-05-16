package com.hacakthon.team1.user.application.dto.request;

import java.util.List;

public record UpdateUserRequest(
        String name,
        String school,
        String department,
        List<String> techStacks,
        String bio,
        String githubUrl,
        String blogUrl,
        String portfolioUrl
) {}
