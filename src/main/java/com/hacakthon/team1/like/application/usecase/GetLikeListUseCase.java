package com.hacakthon.team1.like.application.usecase;

import com.hacakthon.team1.like.application.dto.response.LikeResponse;
import com.hacakthon.team1.like.application.mapper.LikeMapper;
import com.hacakthon.team1.like.domain.service.LikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetLikeListUseCase {

    private final LikeQueryService likeQueryService;

    @Transactional(readOnly = true)
    public List<LikeResponse> getLikes(Long userId) {
        return likeQueryService.findAllByUserId(userId)
                .stream()
                .map(LikeMapper::toResponse)
                .toList();
    }
}
