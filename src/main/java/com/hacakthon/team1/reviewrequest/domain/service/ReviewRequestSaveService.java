package com.hacakthon.team1.reviewrequest.domain.service;

import com.hacakthon.team1.reviewrequest.domain.entity.ReviewRequest;
import com.hacakthon.team1.reviewrequest.domain.repository.ReviewRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewRequestSaveService {

    private final ReviewRequestRepository reviewRequestRepository;

    public ReviewRequest save(ReviewRequest reviewRequest) {
        return reviewRequestRepository.save(reviewRequest);
    }
}
