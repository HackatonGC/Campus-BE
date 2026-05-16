package com.hacakthon.team1.like.application.usecase;

import com.hacakthon.team1.like.application.exception.DuplicateLikeException;
import com.hacakthon.team1.like.domain.entity.Like;
import com.hacakthon.team1.like.domain.service.LikeSaveService;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AddLikeUseCase {

    private final LikeSaveService likeSaveService;
    private final UserQueryService userQueryService;
    private final ProjectQueryService projectQueryService;
    private final ProjectSaveService projectSaveService;

    @Transactional
    public void addLike(Long userId, Long projectId) {
        if (likeSaveService.existsByUserIdAndProjectId(userId, projectId)) {
            throw new DuplicateLikeException();
        }

        User user = userQueryService.findById(userId);
        Project project = projectQueryService.findById(projectId);

        likeSaveService.save(Like.builder()
                .user(user)
                .project(project)
                .build());

        project.incrementLikeCount();
        projectSaveService.save(project);
    }
}
