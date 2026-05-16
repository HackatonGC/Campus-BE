package com.hacakthon.team1.comment.application.usecase;

import com.hacakthon.team1.comment.application.dto.request.CreateCommentRequest;
import com.hacakthon.team1.comment.application.dto.response.CommentResponse;
import com.hacakthon.team1.comment.application.mapper.CommentMapper;
import com.hacakthon.team1.comment.domain.entity.Comment;
import com.hacakthon.team1.comment.domain.service.CommentSaveService;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateCommentUseCase {

    private final UserQueryService userQueryService;
    private final ProjectQueryService projectQueryService;
    private final CommentSaveService commentSaveService;

    @Transactional
    public CommentResponse create(Long userId, Long projectId, CreateCommentRequest request) {
        User user = userQueryService.findById(userId);
        projectQueryService.findById(projectId);

        Comment comment = Comment.builder()
                .user(user)
                .projectId(projectId)
                .content(request.content())
                .parentId(request.parentId())
                .build();

        Comment saved = commentSaveService.save(comment);
        return CommentMapper.toResponse(saved, List.of());
    }
}
