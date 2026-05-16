package com.hacakthon.team1.bookmark.application.usecase;

import com.hacakthon.team1.bookmark.domain.entity.Bookmark;
import com.hacakthon.team1.bookmark.domain.service.BookmarkQueryService;
import com.hacakthon.team1.bookmark.domain.service.BookmarkSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RemoveBookmarkUseCase {

    private final BookmarkQueryService bookmarkQueryService;
    private final BookmarkSaveService bookmarkSaveService;

    @Transactional
    public void removeBookmark(Long userId, Long projectId) {
        Bookmark bookmark = bookmarkQueryService.findByUserIdAndProjectId(userId, projectId);
        bookmarkSaveService.delete(bookmark);
    }
}
