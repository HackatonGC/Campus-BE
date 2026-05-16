package com.hacakthon.team1.teamapplication.application.mapper;

import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;

public class TeamApplicationMapper {

    public static TeamApplicationResponse toResponse(TeamApplication teamApplication) {
        return new TeamApplicationResponse(
                teamApplication.getId(),
                teamApplication.getUser().getId(),
                teamApplication.getUser().getName(),
                teamApplication.getProjectId(),
                teamApplication.getMessage(),
                teamApplication.getStatus(),
                teamApplication.getCreatedAt()
        );
    }
}
