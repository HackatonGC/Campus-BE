package com.hacakthon.team1.like.application.mapper;

import com.hacakthon.team1.like.application.dto.response.LikeResponse;
import com.hacakthon.team1.like.domain.entity.Like;
import com.hacakthon.team1.project.domain.entity.Project;

public class LikeMapper {

    public static LikeResponse toResponse(Like like) {
        Project project = like.getProject();
        return new LikeResponse(
                like.getId(),
                project.getId(),
                project.getTitle(),
                project.getSummary(),
                project.getTechStacks(),
                project.getStatus(),
                project.getProjectType(),
                project.getThumbnailUrl(),
                project.getViewCount(),
                project.getLikeCount()
        );
    }
}
