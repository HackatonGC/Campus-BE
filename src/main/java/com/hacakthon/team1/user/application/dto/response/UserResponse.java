package com.hacakthon.team1.user.application.dto.response;

import java.util.List;

public record UserResponse(
        Long id,
        String email,
        String name,
        String school,
        String department,
        List<String> techStacks
) {}
