package com.hacakthon.team1.user.application.mapper;

import com.hacakthon.team1.user.application.dto.response.PortfolioResponse;
import com.hacakthon.team1.user.application.dto.response.UserInfoResponse;
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
                user.getBio(),
                user.getGithubUrl(),
                user.getBlogUrl(),
                user.getPortfolioUrl(),
                user.isProjectPublic(),
                user.isProfilePublic(),
                user.isActivityPublic(),
                user.getCreatedAt()
        );
    }

    public static UserInfoResponse toInfoResponse(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSchool(),
                user.getDepartment(),
                user.getCreatedAt()
        );
    }

    public static PortfolioResponse toPortfolioResponse(User user) {
        return new PortfolioResponse(
                user.getBio(),
                user.getGithubUrl(),
                user.getBlogUrl(),
                user.getPortfolioUrl(),
                user.getTechStacks()
        );
    }
}
