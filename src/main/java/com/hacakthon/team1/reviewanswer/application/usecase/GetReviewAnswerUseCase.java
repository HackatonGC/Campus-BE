package com.hacakthon.team1.reviewanswer.application.usecase;

import com.hacakthon.team1.reviewanswer.application.dto.response.ReviewAnswerResponse;
import com.hacakthon.team1.reviewanswer.application.mapper.ReviewAnswerMapper;
import com.hacakthon.team1.reviewanswer.domain.service.ReviewAnswerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReviewAnswerUseCase {

    private final ReviewAnswerQueryService reviewAnswerQueryService;

    @Transactional(readOnly = true)
    public List<ReviewAnswerResponse> getAnswers(Long reviewRequestId) {
        return reviewAnswerQueryService.findAllByReviewRequestId(reviewRequestId)
                .stream()
                .map(ReviewAnswerMapper::toResponse)
                .toList();
    }
}
