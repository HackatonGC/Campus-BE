package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.project.application.dto.request.CreateProjectRequest;
import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.entity.Recruitment;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateProjectUseCase {

    private final ProjectSaveService projectSaveService;
    private final UserRepository userRepository;

    @Transactional
    public ProjectResponse create(Long userId, CreateProjectRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        Project project = Project.builder()
                .user(user)
                .title(request.title())
                .summary(request.summary())
                .description(request.description())
                .techStacks(request.techStacks())
                .status(request.status())
                .projectType(request.projectType())
                .thumbnailUrl(request.thumbnailUrl())
                .githubUrl(request.githubUrl())
                .deployUrl(request.deployUrl())
                .figmaUrl(request.figmaUrl())
                .notionUrl(request.notionUrl())
                .build();

        Project savedProject = projectSaveService.save(project);

        if (request.recruitments() != null) {
            request.recruitments().forEach(r -> {
                Recruitment recruitment = Recruitment.builder()
                        .project(savedProject)
                        .role(r.role())
                        .count(r.count())
                        .description(r.description())
                        .build();
                savedProject.addRecruitment(recruitment);
            });
        }

        return ProjectMapper.toResponse(savedProject, 0, 0);
    }
}
