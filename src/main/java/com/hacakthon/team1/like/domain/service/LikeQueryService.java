package com.hacakthon.team1.like.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.like.domain.entity.Like;
import com.hacakthon.team1.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeQueryService {

    private final LikeRepository likeRepository;

    public Like findByUserIdAndProjectId(Long userId, Long projectId) {
        return likeRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new BusinessException(ErrorCode.LIKE_NOT_FOUND));
    }

    public List<Like> findAllByUserId(Long userId) {
        return likeRepository.findAllByUserId(userId);
    }
}
