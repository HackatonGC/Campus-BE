package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.comment.domain.repository.CommentRepository;
import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.repository.ProjectRepository;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetProjectByShareTokenUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectSaveService projectSaveService;
    private final CommentRepository commentRepository;
    private final TeamApplicationRepository teamApplicationRepository;

    @Transactional
    public ProjectResponse get(String shareToken) {
        Project project = projectRepository.findByShareToken(shareToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.PROJECT_NOT_FOUND));

        project.incrementViewCount();
        projectSaveService.save(project);

        long commentCount = commentRepository.countByProjectId(project.getId());
        long totalApplicationCount = teamApplicationRepository.countByProjectId(project.getId());
        Map<Long, Long> acceptedCountMap = project.getRecruitments().stream()
                .collect(Collectors.toMap(
                        r -> r.getId(),
                        r -> teamApplicationRepository.countByRecruitmentIdAndStatus(r.getId(), ApplicationStatus.ACCEPTED)
                ));

        return ProjectMapper.toResponse(project, commentCount, totalApplicationCount, acceptedCountMap);
    }
}
