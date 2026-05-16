package com.hacakthon.team1.bookmark.application.usecase;

import com.hacakthon.team1.bookmark.application.dto.response.BookmarkResponse;
import com.hacakthon.team1.bookmark.application.mapper.BookmarkMapper;
import com.hacakthon.team1.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetBookmarkListUseCase {

    private final BookmarkQueryService bookmarkQueryService;

    @Transactional(readOnly = true)
    public List<BookmarkResponse> getBookmarks(Long userId) {
        return bookmarkQueryService.findAllByUserId(userId)
                .stream()
                .map(BookmarkMapper::toResponse)
                .toList();
    }
}
