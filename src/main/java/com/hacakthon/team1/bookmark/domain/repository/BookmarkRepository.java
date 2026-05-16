package com.hacakthon.team1.bookmark.domain.repository;

import com.hacakthon.team1.bookmark.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);

    Optional<Bookmark> findByUserIdAndProjectId(Long userId, Long projectId);

    List<Bookmark> findAllByUserId(Long userId);
}
