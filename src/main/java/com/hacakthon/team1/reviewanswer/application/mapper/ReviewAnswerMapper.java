package com.hacakthon.team1.reviewanswer.application.mapper;

import com.hacakthon.team1.reviewanswer.application.dto.response.ReviewAnswerResponse;
import com.hacakthon.team1.reviewanswer.domain.entity.ReviewAnswer;

public class ReviewAnswerMapper {

    public static ReviewAnswerResponse toResponse(ReviewAnswer answer) {
        return new ReviewAnswerResponse(
                answer.getId(),
                answer.getReviewRequest().getId(),
                answer.getAuthor().getId(),
                answer.getAuthor().getName(),
                answer.getContent(),
                answer.getCreatedAt()
        );
    }
}
