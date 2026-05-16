package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.teamapplication.application.exception.ProjectNotFoundException;
import com.hacakthon.team1.teamapplication.application.exception.ProjectNotRecruitingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectValidator {

    private final ProjectQueryService projectQueryService;

    public Project validateProjectExists(Long projectId) {
        try {
            return projectQueryService.findById(projectId);
        } catch (Exception e) {
            throw new ProjectNotFoundException();
        }
    }

    public void validateProjectRecruiting(Project project) {
        if (project.getStatus() != ProjectStatus.RECRUITING) {
            throw new ProjectNotRecruitingException();
        }
    }
}
