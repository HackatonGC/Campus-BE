package com.hacakthon.team1.reviewrequest.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.reviewrequest.domain.entity.ReviewRequest;
import com.hacakthon.team1.reviewrequest.domain.repository.ReviewRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewRequestQueryService {

    private final ReviewRequestRepository reviewRequestRepository;

    public ReviewRequest findById(Long id) {
        return reviewRequestRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_REQUEST_NOT_FOUND));
    }

    public List<ReviewRequest> findAllByProjectId(Long projectId) {
        return reviewRequestRepository.findAllByProjectId(projectId);
    }

    public List<ReviewRequest> findAllByRequesterId(Long requesterId) {
        return reviewRequestRepository.findAllByRequesterId(requesterId);
    }
}
