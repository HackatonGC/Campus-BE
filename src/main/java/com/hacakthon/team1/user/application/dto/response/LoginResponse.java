package com.hacakthon.team1.user.application.dto.response;

import java.util.List;

public record LoginResponse(
        String token,
        Long userId,
        String name,
        String school,
        String department,
        List<String> techStacks
) {}
