package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.comment.domain.repository.CommentRepository;
import com.hacakthon.team1.project.application.dto.request.UpdateProjectRequest;
import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.entity.Recruitment;
import com.hacakthon.team1.project.domain.entity.TeamMember;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateProjectUseCase {

    private final ProjectQueryService projectQueryService;
    private final ProjectSaveService projectSaveService;
    private final CommentRepository commentRepository;
    private final TeamApplicationRepository teamApplicationRepository;

    @Transactional
    public ProjectResponse update(Long projectId, Long userId, UpdateProjectRequest request) {
        Project project = projectQueryService.findById(projectId);
        if (!project.getUser().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        project.update(
                request.title(), request.summary(), request.description(),
                request.techStacks(), request.status(), request.projectType(),
                request.thumbnailUrl(), request.githubUrl(), request.deployUrl(),
                request.figmaUrl(), request.notionUrl(),
                request.duration(), request.meetingType(), request.deadline(),
                request.recruitMessage(),
                request.startDate(), request.endDate(), request.myRole(), request.features(),
                request.demoImages(), request.hardPart(), request.learned(), request.messageToJunior()
        );

        if (request.recruitments() != null) {
            project.clearRecruitments();
            request.recruitments().forEach(r -> project.addRecruitment(
                    Recruitment.builder()
                            .project(project)
                            .role(r.role())
                            .count(r.count())
                            .skills(r.skills())
                            .description(r.description())
                            .build()
            ));
        }

        if (request.teamMembers() != null) {
            project.clearTeamMembers();
            request.teamMembers().forEach(m -> project.addTeamMember(
                    TeamMember.builder()
                            .project(project)
                            .role(m.role())
                            .count(m.count())
                            .build()
            ));
        }

        projectSaveService.save(project);

        long commentCount = commentRepository.countByProjectId(projectId);
        long totalApplicationCount = teamApplicationRepository.countByProjectId(projectId);
        return ProjectMapper.toResponse(project, commentCount, totalApplicationCount);
    }
}
