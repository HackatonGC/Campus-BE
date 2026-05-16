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

    @Transactional
    public void apply(Long userId, TeamApplicationRequest request) {
        if (teamApplicationSaveService.existsByUserIdAndProjectId(userId, request.projectId())) {
            throw new DuplicateApplicationException();
        }

        User user = userQueryService.findById(userId);

        TeamApplication teamApplication = TeamApplication.builder()
                .user(user)
                .projectId(request.projectId())
                .message(request.message())
                .build();

        teamApplicationSaveService.save(teamApplication);
    }
}
