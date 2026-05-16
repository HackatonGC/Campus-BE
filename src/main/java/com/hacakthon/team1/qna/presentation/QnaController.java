package com.hacakthon.team1.qna.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import com.hacakthon.team1.qna.application.dto.request.CreateQnaAnswerRequest;
import com.hacakthon.team1.qna.application.dto.request.CreateQnaRequest;
import com.hacakthon.team1.qna.application.dto.response.QnaAnswerResponse;
import com.hacakthon.team1.qna.application.dto.response.QnaResponse;
import com.hacakthon.team1.qna.application.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qnas")
@RequiredArgsConstructor
@Tag(name = "QnA", description = "Q&A 관련 API")
public class QnaController {

    private final CreateQnaUseCase createQnaUseCase;
    private final GetQnaListUseCase getQnaListUseCase;
    private final GetQnaUseCase getQnaUseCase;
    private final DeleteQnaUseCase deleteQnaUseCase;
    private final CreateQnaAnswerUseCase createQnaAnswerUseCase;
    private final GetQnaAnswerListUseCase getQnaAnswerListUseCase;
    private final DeleteQnaAnswerUseCase deleteQnaAnswerUseCase;
    private final ToggleQnaLikeUseCase toggleQnaLikeUseCase;
    private final ToggleQnaAnswerLikeUseCase toggleQnaAnswerLikeUseCase;
    private final AcceptQnaAnswerUseCase acceptQnaAnswerUseCase;

    @PostMapping
    @Operation(summary = "질문 생성")
    public ResponseEntity<CommonResponse<QnaResponse>> create(
            @CurrentUser Long userId,
            @Valid @RequestBody CreateQnaRequest request) {
        QnaResponse response = createQnaUseCase.create(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.QNA_CREATED, response));
    }

    @GetMapping
    @Operation(summary = "질문 목록 조회")
    public ResponseEntity<CommonResponse<List<QnaResponse>>> getList(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<String> tags,
            @RequestParam(defaultValue = "latest") String sort) {
        List<QnaResponse> responses = getQnaListUseCase.getList(keyword, tags, sort);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.QNA_LIST_FOUND, responses));
    }

    @GetMapping("/{id}")
    @Operation(summary = "질문 상세 조회")
    public ResponseEntity<CommonResponse<QnaResponse>> get(@PathVariable Long id) {
        QnaResponse response = getQnaUseCase.get(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.QNA_FOUND, response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "질문 삭제")
    public ResponseEntity<CommonResponse<Void>> delete(
            @PathVariable Long id,
            @CurrentUser Long userId) {
        deleteQnaUseCase.delete(userId, id);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.QNA_DELETED));
    }

    @PostMapping("/{id}/likes")
    @Operation(summary = "질문 좋아요 토글")
    public ResponseEntity<CommonResponse<Void>> toggleLike(
            @PathVariable Long id,
            @CurrentUser Long userId) {
        boolean liked = toggleQnaLikeUseCase.toggle(userId, id);
        ResponseMessage message = liked ? ResponseMessage.QNA_LIKED : ResponseMessage.QNA_UNLIKED;
        return ResponseEntity.ok(CommonResponse.success(message));
    }

    @PostMapping("/{id}/answers")
    @Operation(summary = "답변 작성")
    public ResponseEntity<CommonResponse<QnaAnswerResponse>> createAnswer(
            @PathVariable Long id,
            @CurrentUser Long userId,
            @Valid @RequestBody CreateQnaAnswerRequest request) {
        QnaAnswerResponse response = createQnaAnswerUseCase.create(userId, id, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.QNA_ANSWER_CREATED, response));
    }

    @GetMapping("/{id}/answers")
    @Operation(summary = "답변 목록 조회")
    public ResponseEntity<CommonResponse<List<QnaAnswerResponse>>> getAnswers(@PathVariable Long id) {
        List<QnaAnswerResponse> responses = getQnaAnswerListUseCase.getList(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.QNA_ANSWER_LIST_FOUND, responses));
    }

    @DeleteMapping("/{id}/answers/{answerId}")
    @Operation(summary = "답변 삭제")
    public ResponseEntity<CommonResponse<Void>> deleteAnswer(
            @PathVariable Long id,
            @PathVariable Long answerId,
            @CurrentUser Long userId) {
        deleteQnaAnswerUseCase.delete(userId, id, answerId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.QNA_ANSWER_DELETED));
    }

    @PostMapping("/{id}/answers/{answerId}/likes")
    @Operation(summary = "답변 좋아요 토글")
    public ResponseEntity<CommonResponse<Void>> toggleAnswerLike(
            @PathVariable Long id,
            @PathVariable Long answerId,
            @CurrentUser Long userId) {
        boolean liked = toggleQnaAnswerLikeUseCase.toggle(userId, answerId);
        ResponseMessage message = liked ? ResponseMessage.QNA_ANSWER_LIKED : ResponseMessage.QNA_ANSWER_UNLIKED;
        return ResponseEntity.ok(CommonResponse.success(message));
    }

    @PostMapping("/{id}/answers/{answerId}/accept")
    @Operation(summary = "답변 채택")
    public ResponseEntity<CommonResponse<Void>> acceptAnswer(
            @PathVariable Long id,
            @PathVariable Long answerId,
            @CurrentUser Long userId) {
        acceptQnaAnswerUseCase.accept(userId, id, answerId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.QNA_ANSWER_ACCEPTED));
    }
}
