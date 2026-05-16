package com.hacakthon.team1.comment.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.comment.domain.entity.Comment;
import com.hacakthon.team1.comment.domain.service.CommentQueryService;
import com.hacakthon.team1.comment.domain.service.CommentSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteCommentUseCase {

    private final CommentQueryService commentQueryService;
    private final CommentSaveService commentSaveService;

    @Transactional
    public void delete(Long userId, Long commentId) {
        Comment comment = commentQueryService.findById(commentId);
        if (!comment.getUser().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        commentSaveService.delete(comment);
    }
}
