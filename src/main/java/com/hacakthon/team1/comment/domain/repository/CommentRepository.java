package com.hacakthon.team1.comment.domain.repository;

import com.hacakthon.team1.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProjectIdOrderByCreatedAtAsc(Long projectId);
    long countByProjectId(Long projectId);
    List<Comment> findAllByUserId(Long userId);
}
