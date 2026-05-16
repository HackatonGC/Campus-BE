package com.hacakthon.team1.project.application.mapper;

import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.dto.response.ProjectSummaryResponse;
import com.hacakthon.team1.project.application.dto.response.RecruitmentResponse;
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
                project.getViewCount(),
                project.getLikeCount(),
                project.getUser().getId(),
                project.getUser().getName(),
                project.getUser().getSchool()
        );
    }

    public static ProjectResponse toResponse(Project project, long commentCount, long totalApplicationCount) {
        List<RecruitmentResponse> recruitments = project.getRecruitments().stream()
                .map(r -> new RecruitmentResponse(r.getId(), r.getRole(), r.getCount(), r.getDescription()))
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
                project.getViewCount(),
                project.getLikeCount(),
                commentCount,
                totalApplicationCount,
                project.getUser().getName(),
                project.getUser().getSchool(),
                recruitments,
                project.getCreatedAt()
        );
    }
}
