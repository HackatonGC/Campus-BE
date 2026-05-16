package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.repository.ProjectRepository;
import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.application.mapper.TeamApplicationMapper;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetTeamApplicationListUseCase {

    private final TeamApplicationQueryService teamApplicationQueryService;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<TeamApplicationResponse> getByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return teamApplicationQueryService.findAllByProjectId(projectId)
                .stream()
                .map(a -> TeamApplicationMapper.toResponse(a, project))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TeamApplicationResponse> getByUser(Long userId) {
        Map<Long, Project> projectMap = projectRepository.findAll().stream()
                .collect(Collectors.toMap(Project::getId, p -> p));

        return teamApplicationQueryService.findAllByUserId(userId)
                .stream()
                .map(a -> TeamApplicationMapper.toResponse(a, projectMap.get(a.getProjectId())))
                .toList();
    }
}
