package com.hacakthon.team1.bookmark.domain.service;

import com.hacakthon.team1.bookmark.domain.entity.Bookmark;
import com.hacakthon.team1.bookmark.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkSaveService {

    private final BookmarkRepository bookmarkRepository;

    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public void delete(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }

    public boolean existsByUserIdAndProjectId(Long userId, Long projectId) {
        return bookmarkRepository.existsByUserIdAndProjectId(userId, projectId);
    }
}
