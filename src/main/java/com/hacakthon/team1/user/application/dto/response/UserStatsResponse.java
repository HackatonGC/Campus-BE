package com.hacakthon.team1.user.application.dto.response;

public record UserStatsResponse(
        long projectCount,
        long totalLikeCount,
        long codeReviewCount,
        long teamParticipationCount
) {}
