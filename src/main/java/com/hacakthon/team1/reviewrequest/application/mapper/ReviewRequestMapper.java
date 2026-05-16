package com.hacakthon.team1.reviewrequest.application.mapper;

import com.hacakthon.team1.reviewrequest.application.dto.response.ReviewRequestDetailResponse;
import com.hacakthon.team1.reviewrequest.application.dto.response.ReviewRequestSummaryResponse;
import com.hacakthon.team1.reviewrequest.domain.entity.ReviewRequest;

public class ReviewRequestMapper {

    public static ReviewRequestSummaryResponse toSummaryResponse(ReviewRequest reviewRequest) {
        return new ReviewRequestSummaryResponse(
                reviewRequest.getId(),
                reviewRequest.getProjectId(),
                reviewRequest.getTitle(),
                reviewRequest.getRequester().getId(),
                reviewRequest.getCreatedAt()
        );
    }

    public static ReviewRequestDetailResponse toDetailResponse(ReviewRequest reviewRequest) {
        return new ReviewRequestDetailResponse(
                reviewRequest.getId(),
                reviewRequest.getProjectId(),
                reviewRequest.getTitle(),
                reviewRequest.getContent(),
                reviewRequest.getGithubUrl(),
                reviewRequest.getRequester().getId(),
                reviewRequest.getCreatedAt()
        );
    }
}
