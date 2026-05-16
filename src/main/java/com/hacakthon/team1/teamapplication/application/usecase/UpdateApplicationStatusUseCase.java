package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.teamapplication.application.dto.request.UpdateApplicationStatusRequest;
import com.hacakthon.team1.teamapplication.application.exception.ApplicationAlreadyProcessedException;
import com.hacakthon.team1.teamapplication.application.exception.InvalidApplicationStatusException;
import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationQueryService;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateApplicationStatusUseCase {

    private final TeamApplicationQueryService teamApplicationQueryService;
    private final TeamApplicationSaveService teamApplicationSaveService;

    @Transactional
    public void updateStatus(Long applicationId, UpdateApplicationStatusRequest request) {
        if (request.status() == ApplicationStatus.PENDING) {
            throw new InvalidApplicationStatusException();
        }

        TeamApplication teamApplication = teamApplicationQueryService.findById(applicationId);

        if (teamApplication.getStatus() != ApplicationStatus.PENDING) {
            throw new ApplicationAlreadyProcessedException();
        }

        teamApplication.updateStatus(request.status());
        teamApplicationSaveService.save(teamApplication);
    }
}
