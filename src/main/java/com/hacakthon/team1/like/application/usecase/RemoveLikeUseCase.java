package com.hacakthon.team1.like.application.usecase;

import com.hacakthon.team1.like.domain.entity.Like;
import com.hacakthon.team1.like.domain.service.LikeQueryService;
import com.hacakthon.team1.like.domain.service.LikeSaveService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RemoveLikeUseCase {

    private final LikeQueryService likeQueryService;
    private final LikeSaveService likeSaveService;
    private final ProjectSaveService projectSaveService;

    @Transactional
    public void removeLike(Long userId, Long projectId) {
        Like like = likeQueryService.findByUserIdAndProjectId(userId, projectId);
        like.getProject().decrementLikeCount();
        projectSaveService.save(like.getProject());
        likeSaveService.delete(like);
    }
}
