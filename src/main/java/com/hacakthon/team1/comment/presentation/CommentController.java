package com.hacakthon.team1.comment.presentation;

import com.hacakthon.team1.comment.application.dto.request.CreateCommentRequest;
import com.hacakthon.team1.comment.application.dto.response.CommentResponse;
import com.hacakthon.team1.comment.application.usecase.CreateCommentUseCase;
import com.hacakthon.team1.comment.application.usecase.DeleteCommentUseCase;
import com.hacakthon.team1.comment.application.usecase.GetCommentListUseCase;
import com.hacakthon.team1.comment.application.usecase.LikeCommentUseCase;
import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;
    private final GetCommentListUseCase getCommentListUseCase;
    private final LikeCommentUseCase likeCommentUseCase;

    @PostMapping
    @Operation(summary = "댓글/대댓글 작성")
    public ResponseEntity<CommonResponse<CommentResponse>> create(
            @PathVariable Long projectId,
            @RequestBody CreateCommentRequest request,
            @CurrentUser Long userId) {
        CommentResponse response = createCommentUseCase.create(userId, projectId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.COMMENT_CREATED, response));
    }

    @GetMapping
    @Operation(summary = "댓글 목록 조회")
    public ResponseEntity<CommonResponse<List<CommentResponse>>> getList(
            @PathVariable Long projectId) {
        List<CommentResponse> response = getCommentListUseCase.getList(projectId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.COMMENT_LIST_FOUND, response));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<CommonResponse<Void>> delete(
            @PathVariable Long projectId,
            @PathVariable Long commentId,
            @CurrentUser Long userId) {
        deleteCommentUseCase.delete(userId, commentId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.COMMENT_DELETED, null));
    }

    @PostMapping("/{commentId}/likes")
    @Operation(summary = "댓글 좋아요")
    public ResponseEntity<CommonResponse<Void>> like(
            @PathVariable Long projectId,
            @PathVariable Long commentId,
            @CurrentUser Long userId) {
        likeCommentUseCase.like(userId, commentId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.COMMENT_LIKED, null));
    }

    @DeleteMapping("/{commentId}/likes")
    @Operation(summary = "댓글 좋아요 취소")
    public ResponseEntity<CommonResponse<Void>> unlike(
            @PathVariable Long projectId,
            @PathVariable Long commentId,
            @CurrentUser Long userId) {
        likeCommentUseCase.unlike(userId, commentId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.COMMENT_UNLIKED, null));
    }
}
