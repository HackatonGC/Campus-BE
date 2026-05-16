package com.hacakthon.team1.reviewrequest.application.usecase;

import com.hacakthon.team1.reviewrequest.application.dto.request.ReviewRequestRequest;
import com.hacakthon.team1.reviewrequest.domain.entity.ReviewRequest;
import com.hacakthon.team1.reviewrequest.domain.service.ReviewRequestSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateReviewRequestUseCase {

    private final ReviewRequestSaveService reviewRequestSaveService;
    private final UserQueryService userQueryService;

    @Transactional
    public void createReviewRequest(Long userId, Long projectId, ReviewRequestRequest request) {
        User requester = userQueryService.findById(userId);

        ReviewRequest reviewRequest = ReviewRequest.builder()
                .requester(requester)
                .projectId(projectId)
                .title(request.title())
                .content(request.content())
                .githubUrl(request.githubUrl())
                .build();

        reviewRequestSaveService.save(reviewRequest);
    }
}
