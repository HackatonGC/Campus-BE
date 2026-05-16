package com.hacakthon.team1.user.application.dto.request;

public record UpdateUserInfoRequest(
        String name,
        String school,
        String department
) {}
