package com.hacakthon.team1.user.application.dto.request;

public record UpdatePrivacyRequest(
        boolean isProjectPublic,
        boolean isProfilePublic,
        boolean isActivityPublic
) {}
