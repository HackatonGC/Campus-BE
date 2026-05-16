package com.hacakthon.team1.reviewanswer.application.usecase;

import com.hacakthon.team1.reviewanswer.application.dto.request.ReviewAnswerRequest;
import com.hacakthon.team1.reviewanswer.domain.entity.ReviewAnswer;
import com.hacakthon.team1.reviewanswer.domain.service.ReviewAnswerSaveService;
import com.hacakthon.team1.reviewrequest.domain.entity.ReviewRequest;
import com.hacakthon.team1.reviewrequest.domain.service.ReviewRequestQueryService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateReviewAnswerUseCase {

    private final ReviewAnswerSaveService reviewAnswerSaveService;
    private final ReviewRequestQueryService reviewRequestQueryService;
    private final UserQueryService userQueryService;

    @Transactional
    public void createAnswer(Long userId, Long reviewRequestId, ReviewAnswerRequest request) {
        User author = userQueryService.findById(userId);
        ReviewRequest reviewRequest = reviewRequestQueryService.findById(reviewRequestId);

        ReviewAnswer answer = ReviewAnswer.builder()
                .reviewRequest(reviewRequest)
                .author(author)
                .content(request.content())
                .build();

        reviewAnswerSaveService.save(answer);
    }
}
