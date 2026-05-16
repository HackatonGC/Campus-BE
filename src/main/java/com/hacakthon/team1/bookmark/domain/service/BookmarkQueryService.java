package com.hacakthon.team1.bookmark.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.bookmark.domain.entity.Bookmark;
import com.hacakthon.team1.bookmark.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkQueryService {

    private final BookmarkRepository bookmarkRepository;

    public Bookmark findByUserIdAndProjectId(Long userId, Long projectId) {
        return bookmarkRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOOKMARK_NOT_FOUND));
    }

    public List<Bookmark> findAllByUserId(Long userId) {
        return bookmarkRepository.findAllByUserId(userId);
    }
}
