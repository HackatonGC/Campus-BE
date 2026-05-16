package com.hacakthon.team1.reviewanswer.domain.service;

import com.hacakthon.team1.reviewanswer.domain.entity.ReviewAnswer;
import com.hacakthon.team1.reviewanswer.domain.repository.ReviewAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewAnswerQueryService {

    private final ReviewAnswerRepository reviewAnswerRepository;

    public List<ReviewAnswer> findAllByReviewRequestId(Long reviewRequestId) {
        return reviewAnswerRepository.findAllByReviewRequestId(reviewRequestId);
    }
}
