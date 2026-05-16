package com.hacakthon.team1.like.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import com.hacakthon.team1.like.application.dto.response.LikeResponse;
import com.hacakthon.team1.like.application.usecase.AddLikeUseCase;
import com.hacakthon.team1.like.application.usecase.GetLikeListUseCase;
import com.hacakthon.team1.like.application.usecase.RemoveLikeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Like", description = "좋아요 관련 API")
public class LikeController {

    private final AddLikeUseCase addLikeUseCase;
    private final RemoveLikeUseCase removeLikeUseCase;
    private final GetLikeListUseCase getLikeListUseCase;

    @PostMapping("/api/v1/projects/{projectId}/likes")
    @Operation(summary = "좋아요 추가", description = "프로젝트에 좋아요를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "좋아요 추가 완료"),
            @ApiResponse(responseCode = "404", description = "프로젝트 또는 유저 없음"),
            @ApiResponse(responseCode = "409", description = "이미 좋아요한 프로젝트")
    })
    public ResponseEntity<CommonResponse<Void>> addLike(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @CurrentUser Long userId
    ) {
        addLikeUseCase.addLike(userId, projectId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.LIKE_ADDED));
    }

    @DeleteMapping("/api/v1/projects/{projectId}/likes")
    @Operation(summary = "좋아요 삭제", description = "프로젝트 좋아요를 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "좋아요 삭제 완료"),
            @ApiResponse(responseCode = "404", description = "좋아요 없음")
    })
    public ResponseEntity<CommonResponse<Void>> removeLike(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @CurrentUser Long userId
    ) {
        removeLikeUseCase.removeLike(userId, projectId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.LIKE_REMOVED));
    }

    @GetMapping("/api/v1/users/me/likes")
    @Operation(summary = "내 좋아요 목록 조회", description = "내가 좋아요한 프로젝트 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<CommonResponse<List<LikeResponse>>> getLikes(
            @CurrentUser Long userId
    ) {
        List<LikeResponse> responses = getLikeListUseCase.getLikes(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.LIKE_LIST_FOUND, responses));
    }
}
