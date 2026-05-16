package com.hacakthon.team1.reviewanswer.domain.service;

import com.hacakthon.team1.reviewanswer.domain.entity.ReviewAnswer;
import com.hacakthon.team1.reviewanswer.domain.repository.ReviewAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewAnswerSaveService {

    private final ReviewAnswerRepository reviewAnswerRepository;

    public void save(ReviewAnswer reviewAnswer) {
        reviewAnswerRepository.save(reviewAnswer);
    }
}
