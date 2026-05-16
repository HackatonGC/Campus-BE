package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.teamapplication.application.dto.request.TeamApplicationRequest;
import com.hacakthon.team1.teamapplication.application.exception.DuplicateApplicationException;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ApplyTeamUseCase {

    private final TeamApplicationSaveService teamApplicationSaveService;
    private final UserQueryService userQueryService;
    private final ProjectValidator projectValidator;

    @Transactional
    public void apply(Long userId, Long projectId, TeamApplicationRequest request) {
        projectValidator.validateProjectExists(projectId);
        projectValidator.validateProjectRecruiting(projectId);

        if (teamApplicationSaveService.existsByUserIdAndProjectId(userId, projectId)) {
            throw new DuplicateApplicationException();
        }

        User user = userQueryService.findById(userId);

        TeamApplication teamApplication = TeamApplication.builder()
                .user(user)
                .projectId(projectId)
                .role(request.role())
                .message(request.message())
                .build();

        teamApplicationSaveService.save(teamApplication);
    }
}
