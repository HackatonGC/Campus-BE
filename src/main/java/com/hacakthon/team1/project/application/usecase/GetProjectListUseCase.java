package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.project.application.dto.response.ProjectSummaryResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetProjectListUseCase {

    private final ProjectQueryService projectQueryService;

    @Transactional(readOnly = true)
    public List<ProjectSummaryResponse> getList() {
        return projectQueryService.findAll().stream()
                .map(ProjectMapper::toSummaryResponse)
                .toList();
    }
}
