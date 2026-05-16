package com.hacakthon.team1.teamapplication.application.mapper;

import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;

public class TeamApplicationMapper {

    public static TeamApplicationResponse toResponse(TeamApplication teamApplication, Project project) {
        return new TeamApplicationResponse(
                teamApplication.getId(),
                teamApplication.getUser().getId(),
                teamApplication.getUser().getName(),
                teamApplication.getProjectId(),
                project != null ? project.getTitle() : null,
                project != null ? project.getSummary() : null,
                project != null ? project.getTechStacks() : null,
                project != null ? project.getStatus() : null,
                project != null ? project.getUser().getName() : null,
                teamApplication.getRole(),
                teamApplication.getMessage(),
                teamApplication.getStatus(),
                teamApplication.getCreatedAt()
        );
    }
}
