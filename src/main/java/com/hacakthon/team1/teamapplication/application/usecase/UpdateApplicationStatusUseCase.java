package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import com.hacakthon.team1.teamapplication.application.dto.request.UpdateApplicationStatusRequest;
import com.hacakthon.team1.teamapplication.application.exception.ApplicationAlreadyProcessedException;
import com.hacakthon.team1.teamapplication.application.exception.InvalidApplicationStatusException;
import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
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
    private final TeamApplicationRepository teamApplicationRepository;
    private final ProjectQueryService projectQueryService;
    private final ProjectSaveService projectSaveService;

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

        if (request.status() == ApplicationStatus.ACCEPTED) {
            checkAndTransitionToDevloping(teamApplication.getProjectId());
        }
    }

    private void checkAndTransitionToDevloping(Long projectId) {
        Project project = projectQueryService.findById(projectId);
        if (project.getStatus() != ProjectStatus.RECRUITING) return;

        int totalSlots = project.getRecruitments().stream()
                .mapToInt(r -> r.getCount())
                .sum();
        if (totalSlots == 0) return;

        long acceptedCount = teamApplicationRepository.countByProjectIdAndStatus(projectId, ApplicationStatus.ACCEPTED);
        if (acceptedCount >= totalSlots) {
            project.updateStatus(ProjectStatus.DEVELOPING);
            projectSaveService.save(project);
        }
    }
}
