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
    public List<ProjectSummaryResponse> getList(String keyword, String techStack, boolean recruitingOnly) {
        return projectQueryService.search(keyword, techStack, recruitingOnly).stream()
                .map(ProjectMapper::toSummaryResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<String> getPopularTags(int limit) {
        return projectQueryService.findPopularTechStacks(limit);
    }
}
