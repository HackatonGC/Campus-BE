package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteProjectUseCase {

    private final ProjectQueryService projectQueryService;
    private final ProjectSaveService projectSaveService;

    @Transactional
    public void delete(Long projectId, Long userId) {
        Project project = projectQueryService.findById(projectId);

        if (!project.getUser().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        projectSaveService.delete(project);
    }
}
