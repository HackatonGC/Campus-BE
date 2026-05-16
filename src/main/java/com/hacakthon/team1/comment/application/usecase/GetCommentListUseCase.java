package com.hacakthon.team1.comment.application.usecase;

import com.hacakthon.team1.comment.application.dto.response.CommentResponse;
import com.hacakthon.team1.comment.application.mapper.CommentMapper;
import com.hacakthon.team1.comment.domain.entity.Comment;
import com.hacakthon.team1.comment.domain.service.CommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetCommentListUseCase {

    private final CommentQueryService commentQueryService;

    @Transactional(readOnly = true)
    public List<CommentResponse> getList(Long projectId) {
        List<Comment> all = commentQueryService.findAllByProjectId(projectId);

        Map<Long, List<Comment>> repliesMap = all.stream()
                .filter(c -> c.getParentId() != null)
                .collect(Collectors.groupingBy(Comment::getParentId));

        return all.stream()
                .filter(c -> c.getParentId() == null)
                .map(c -> {
                    List<CommentResponse> replies = repliesMap.getOrDefault(c.getId(), List.of()).stream()
                            .map(r -> CommentMapper.toResponse(r, List.of()))
                            .toList();
                    return CommentMapper.toResponse(c, replies);
                })
                .toList();
    }
}
