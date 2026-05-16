package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetProjectUseCase {

    private final ProjectQueryService projectQueryService;
    private final ProjectSaveService projectSaveService;

    @Transactional
    public ProjectResponse get(Long id) {
        Project project = projectQueryService.findById(id);
        project.incrementViewCount();
        projectSaveService.save(project);
        return ProjectMapper.toResponse(project);
    }
}
