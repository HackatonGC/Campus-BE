package com.hacakthon.team1.bookmark.application.usecase;

import com.hacakthon.team1.bookmark.application.exception.DuplicateBookmarkException;
import com.hacakthon.team1.bookmark.domain.entity.Bookmark;
import com.hacakthon.team1.bookmark.domain.service.BookmarkSaveService;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AddBookmarkUseCase {

    private final BookmarkSaveService bookmarkSaveService;
    private final UserQueryService userQueryService;
    private final ProjectQueryService projectQueryService;

    @Transactional
    public void addBookmark(Long userId, Long projectId) {
        if (bookmarkSaveService.existsByUserIdAndProjectId(userId, projectId)) {
            throw new DuplicateBookmarkException();
        }

        User user = userQueryService.findById(userId);
        Project project = projectQueryService.findById(projectId);

        bookmarkSaveService.save(Bookmark.builder()
                .user(user)
                .project(project)
                .build());
    }
}
