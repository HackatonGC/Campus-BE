package com.hacakthon.team1.like.domain.service;

import com.hacakthon.team1.like.domain.entity.Like;
import com.hacakthon.team1.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeSaveService {

    private final LikeRepository likeRepository;

    public Like save(Like like) {
        return likeRepository.save(like);
    }

    public void delete(Like like) {
        likeRepository.delete(like);
    }

    public boolean existsByUserIdAndProjectId(Long userId, Long projectId) {
        return likeRepository.existsByUserIdAndProjectId(userId, projectId);
    }
}
