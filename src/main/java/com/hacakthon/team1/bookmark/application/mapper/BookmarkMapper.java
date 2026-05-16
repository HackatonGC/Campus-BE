package com.hacakthon.team1.bookmark.application.mapper;

import com.hacakthon.team1.bookmark.application.dto.response.BookmarkResponse;
import com.hacakthon.team1.bookmark.domain.entity.Bookmark;
import com.hacakthon.team1.project.domain.entity.Project;

public class BookmarkMapper {

    public static BookmarkResponse toResponse(Bookmark bookmark) {
        Project project = bookmark.getProject();
        return new BookmarkResponse(
                bookmark.getId(),
                project.getId(),
                project.getTitle(),
                project.getSummary(),
                project.getTechStacks(),
                project.getStatus(),
                project.getProjectType(),
                project.getThumbnailUrl(),
                project.getViewCount(),
                project.getLikeCount(),
                project.getUser().getName(),
                project.getCreatedAt()
        );
    }
}
