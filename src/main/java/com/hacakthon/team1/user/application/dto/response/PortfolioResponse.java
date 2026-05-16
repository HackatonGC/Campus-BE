package com.hacakthon.team1.user.application.dto.response;

import java.util.List;

public record PortfolioResponse(
        String bio,
        String githubUrl,
        String blogUrl,
        String portfolioUrl,
        List<String> techStacks
) {}
