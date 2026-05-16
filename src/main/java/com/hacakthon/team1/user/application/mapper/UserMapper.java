package com.hacakthon.team1.user.application.mapper;

import com.hacakthon.team1.user.application.dto.response.UserResponse;
import com.hacakthon.team1.user.domain.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSchool(),
                user.getDepartment(),
                user.getTechStacks(),
                user.getCreatedAt()
        );
    }
}
