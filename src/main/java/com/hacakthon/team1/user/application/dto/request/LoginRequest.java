package com.hacakthon.team1.user.application.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
