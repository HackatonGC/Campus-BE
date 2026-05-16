package com.hacakthon.team1.user.application.dto.request;

import java.util.List;

public record SignUpRequest(
        String email,
        String password,
        String name,
        String school,
        String department,
        List<String> techStacks
) {}
