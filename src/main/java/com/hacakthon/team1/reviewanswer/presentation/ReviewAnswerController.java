package com.hacakthon.team1.reviewanswer.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import com.hacakthon.team1.reviewanswer.application.dto.request.ReviewAnswerRequest;
import com.hacakthon.team1.reviewanswer.application.dto.response.ReviewAnswerResponse;
import com.hacakthon.team1.reviewanswer.application.usecase.CreateReviewAnswerUseCase;
import com.hacakthon.team1.reviewanswer.application.usecase.GetReviewAnswerUseCase;
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
@Tag(name = "ReviewAnswer", description = "코드 리뷰 답변 관련 API")
public class ReviewAnswerController {

    private final CreateReviewAnswerUseCase createReviewAnswerUseCase;
    private final GetReviewAnswerUseCase getReviewAnswerUseCase;

    @PostMapping("/api/v1/reviews/{reviewId}/answers")
    @Operation(summary = "코드 리뷰 답변 작성", description = "코드 리뷰 요청에 답변을 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "답변 작성 완료"),
            @ApiResponse(responseCode = "404", description = "리뷰 요청 또는 유저 없음")
    })
    public ResponseEntity<CommonResponse<Void>> createAnswer(
            @Parameter(description = "리뷰 요청 ID") @PathVariable Long reviewId,
            @CurrentUser Long userId,
            @Valid @RequestBody ReviewAnswerRequest request
    ) {
        createReviewAnswerUseCase.createAnswer(userId, reviewId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.REVIEW_ANSWER_CREATED));
    }

    @GetMapping("/api/v1/reviews/{reviewId}/answers")
    @Operation(summary = "코드 리뷰 답변 목록 조회", description = "코드 리뷰 요청에 달린 답변 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<CommonResponse<List<ReviewAnswerResponse>>> getAnswers(
            @Parameter(description = "리뷰 요청 ID") @PathVariable Long reviewId
    ) {
        List<ReviewAnswerResponse> responses = getReviewAnswerUseCase.getAnswers(reviewId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.REVIEW_ANSWER_LIST_FOUND, responses));
    }
}
