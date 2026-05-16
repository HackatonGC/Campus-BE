package com.hacakthon.team1.comment.domain.repository;

import com.hacakthon.team1.comment.domain.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserIdAndCommentId(Long userId, Long commentId);
    boolean existsByUserIdAndCommentId(Long userId, Long commentId);
}
