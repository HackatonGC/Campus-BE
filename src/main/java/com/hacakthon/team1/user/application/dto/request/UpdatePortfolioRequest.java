package com.hacakthon.team1.user.application.dto.request;

import java.util.List;

public record UpdatePortfolioRequest(
        String bio,
        String githubUrl,
        String blogUrl,
        String portfolioUrl,
        List<String> techStacks
) {}
