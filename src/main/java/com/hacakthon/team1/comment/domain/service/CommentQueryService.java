package com.hacakthon.team1.comment.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.comment.domain.entity.Comment;
import com.hacakthon.team1.comment.domain.entity.CommentLike;
import com.hacakthon.team1.comment.domain.repository.CommentLikeRepository;
import com.hacakthon.team1.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }

    public List<Comment> findAllByProjectId(Long projectId) {
        return commentRepository.findAllByProjectIdOrderByCreatedAtAsc(projectId);
    }

    public long countByProjectId(Long projectId) {
        return commentRepository.countByProjectId(projectId);
    }

    public Optional<CommentLike> findLike(Long userId, Long commentId) {
        return commentLikeRepository.findByUserIdAndCommentId(userId, commentId);
    }

    public boolean existsLike(Long userId, Long commentId) {
        return commentLikeRepository.existsByUserIdAndCommentId(userId, commentId);
    }
}
