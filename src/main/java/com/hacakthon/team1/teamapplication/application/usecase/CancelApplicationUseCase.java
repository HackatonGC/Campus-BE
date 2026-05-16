package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.teamapplication.application.exception.ApplicationAccessDeniedException;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationQueryService;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CancelApplicationUseCase {

    private final TeamApplicationQueryService teamApplicationQueryService;
    private final TeamApplicationSaveService teamApplicationSaveService;

    @Transactional
    public void cancel(Long userId, Long applicationId) {
        TeamApplication teamApplication = teamApplicationQueryService.findById(applicationId);

        if (!teamApplication.getUser().getId().equals(userId)) {
            throw new ApplicationAccessDeniedException();
        }

        teamApplicationSaveService.delete(teamApplication);
    }
}
