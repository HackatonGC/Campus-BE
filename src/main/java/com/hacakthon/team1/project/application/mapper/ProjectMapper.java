package com.hacakthon.team1.project.application.mapper;

import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.dto.response.ProjectSummaryResponse;
import com.hacakthon.team1.project.application.dto.response.RecruitmentResponse;
import com.hacakthon.team1.project.application.dto.response.TeamMemberResponse;
import com.hacakthon.team1.project.domain.entity.Project;

import java.util.List;

public class ProjectMapper {

    public static ProjectSummaryResponse toSummaryResponse(Project project) {
        return new ProjectSummaryResponse(
                project.getId(),
                project.getShareToken(),
                project.getTitle(),
                project.getSummary(),
                project.getTechStacks(),
                project.getStatus(),
                project.getProjectType(),
                project.getThumbnailUrl(),
                project.getDuration(),
                project.getMeetingType(),
                project.getDeadline(),
                project.getStartDate(),
                project.getEndDate(),
                project.getViewCount(),
                project.getLikeCount(),
                project.getUser().getId(),
                project.getUser().getName(),
                project.getUser().getSchool(),
                project.getCreatedAt()
        );
    }

    public static ProjectResponse toResponse(Project project, long commentCount, long totalApplicationCount) {
        List<RecruitmentResponse> recruitments = project.getRecruitments().stream()
                .map(r -> new RecruitmentResponse(r.getId(), r.getRole(), r.getCount(), r.getSkills(), r.getDescription()))
                .toList();

        List<TeamMemberResponse> teamMembers = project.getTeamMembers().stream()
                .map(m -> new TeamMemberResponse(m.getId(), m.getRole(), m.getCount()))
                .toList();

        return new ProjectResponse(
                project.getId(),
                project.getShareToken(),
                project.getTitle(),
                project.getSummary(),
                project.getDescription(),
                project.getTechStacks(),
                project.getStatus(),
                project.getProjectType(),
                project.getThumbnailUrl(),
                project.getGithubUrl(),
                project.getDeployUrl(),
                project.getFigmaUrl(),
                project.getNotionUrl(),
                project.getDuration(),
                project.getMeetingType(),
                project.getDeadline(),
                project.getRecruitMessage(),
                recruitments,
                project.getStartDate(),
                project.getEndDate(),
                project.getMyRole(),
                project.getFeatures(),
                project.getDemoImages(),
                project.getHardPart(),
                project.getLearned(),
                project.getMessageToJunior(),
                teamMembers,
                project.getViewCount(),
                project.getLikeCount(),
                commentCount,
                totalApplicationCount,
                project.getUser().getId(),
                project.getUser().getName(),
                project.getUser().getSchool(),
                project.getCreatedAt()
        );
    }
}
