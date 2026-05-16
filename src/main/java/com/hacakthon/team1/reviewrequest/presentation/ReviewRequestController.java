package com.hacakthon.team1.reviewrequest.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import com.hacakthon.team1.reviewrequest.application.dto.request.ReviewRequestRequest;
import com.hacakthon.team1.reviewrequest.application.dto.response.ReviewRequestDetailResponse;
import com.hacakthon.team1.reviewrequest.application.dto.response.ReviewRequestSummaryResponse;
import com.hacakthon.team1.reviewrequest.application.usecase.CreateReviewRequestUseCase;
import com.hacakthon.team1.reviewrequest.application.usecase.GetReviewRequestUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ReviewRequest", description = "코드 리뷰 요청 관련 API")
public class ReviewRequestController {

    private final CreateReviewRequestUseCase createReviewRequestUseCase;
    private final GetReviewRequestUseCase getReviewRequestUseCase;

    @PostMapping("/api/v1/projects/{projectId}/reviews")
    @Operation(summary = "코드 리뷰 요청 등록", description = "프로젝트에 코드 리뷰 요청을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "요청 등록 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력값"),
            @ApiResponse(responseCode = "404", description = "유저 없음")
    })
    public ResponseEntity<CommonResponse<Void>> createReviewRequest(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @CurrentUser Long userId,
            @Valid @RequestBody ReviewRequestRequest request
    ) {
        createReviewRequestUseCase.createReviewRequest(userId, projectId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.REVIEW_REQUEST_CREATED));
    }

    @GetMapping("/api/v1/projects/{projectId}/reviews")
    @Operation(summary = "프로젝트별 리뷰 요청 목록 조회", description = "특정 프로젝트에 등록된 코드 리뷰 요청 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<CommonResponse<List<ReviewRequestSummaryResponse>>> getByProject(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId
    ) {
        List<ReviewRequestSummaryResponse> responses = getReviewRequestUseCase.getByProject(projectId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.REVIEW_REQUEST_LIST_FOUND, responses));
    }

    @GetMapping("/api/v1/reviews/{reviewId}")
    @Operation(summary = "리뷰 요청 상세 조회", description = "코드 리뷰 요청의 상세 내용을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "리뷰 요청 없음")
    })
    public ResponseEntity<CommonResponse<ReviewRequestDetailResponse>> getReviewRequest(
            @Parameter(description = "리뷰 요청 ID") @PathVariable Long reviewId
    ) {
        ReviewRequestDetailResponse response = getReviewRequestUseCase.getReviewRequest(reviewId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.REVIEW_REQUEST_FOUND, response));
    }

    @GetMapping("/api/v1/reviews/my")
    @Operation(summary = "내 리뷰 요청 목록 조회", description = "내가 등록한 코드 리뷰 요청 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<CommonResponse<List<ReviewRequestSummaryResponse>>> getMyReviewRequests(
            @CurrentUser Long userId
    ) {
        List<ReviewRequestSummaryResponse> responses = getReviewRequestUseCase.getMyReviewRequests(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.REVIEW_REQUEST_LIST_FOUND, responses));
    }
}
