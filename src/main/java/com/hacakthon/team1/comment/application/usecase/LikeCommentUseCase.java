package com.hacakthon.team1.comment.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.comment.domain.entity.Comment;
import com.hacakthon.team1.comment.domain.entity.CommentLike;
import com.hacakthon.team1.comment.domain.service.CommentQueryService;
import com.hacakthon.team1.comment.domain.service.CommentSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LikeCommentUseCase {

    private final CommentQueryService commentQueryService;
    private final CommentSaveService commentSaveService;

    @Transactional
    public void like(Long userId, Long commentId) {
        if (commentQueryService.existsLike(userId, commentId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_COMMENT_LIKE);
        }
        Comment comment = commentQueryService.findById(commentId);
        comment.incrementLikeCount();
        commentSaveService.save(comment);
        commentSaveService.saveLike(CommentLike.builder().userId(userId).commentId(commentId).build());
    }

    @Transactional
    public void unlike(Long userId, Long commentId) {
        CommentLike like = commentQueryService.findLike(userId, commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_LIKE_NOT_FOUND));
        Comment comment = commentQueryService.findById(commentId);
        comment.decrementLikeCount();
        commentSaveService.save(comment);
        commentSaveService.deleteLike(like);
    }
}
