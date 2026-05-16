package com.hacakthon.team1.reviewrequest.application.usecase;

import com.hacakthon.team1.reviewrequest.application.dto.response.ReviewRequestDetailResponse;
import com.hacakthon.team1.reviewrequest.application.dto.response.ReviewRequestSummaryResponse;
import com.hacakthon.team1.reviewrequest.application.mapper.ReviewRequestMapper;
import com.hacakthon.team1.reviewrequest.domain.service.ReviewRequestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReviewRequestUseCase {

    private final ReviewRequestQueryService reviewRequestQueryService;

    @Transactional(readOnly = true)
    public ReviewRequestDetailResponse getReviewRequest(Long reviewId) {
        return ReviewRequestMapper.toDetailResponse(reviewRequestQueryService.findById(reviewId));
    }

    @Transactional(readOnly = true)
    public List<ReviewRequestSummaryResponse> getByProject(Long projectId) {
        return reviewRequestQueryService.findAllByProjectId(projectId)
                .stream()
                .map(ReviewRequestMapper::toSummaryResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewRequestSummaryResponse> getMyReviewRequests(Long userId) {
        return reviewRequestQueryService.findAllByRequesterId(userId)
                .stream()
                .map(ReviewRequestMapper::toSummaryResponse)
                .toList();
    }
}
