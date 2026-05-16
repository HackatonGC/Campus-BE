package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.project.application.dto.request.CreateProjectRequest;
import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.entity.Recruitment;
import com.hacakthon.team1.project.domain.entity.TeamMember;
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
                .duration(request.duration())
                .meetingType(request.meetingType())
                .deadline(request.deadline())
                .recruitMessage(request.recruitMessage())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .myRole(request.myRole())
                .features(request.features())
                .demoImages(request.demoImages())
                .hardPart(request.hardPart())
                .learned(request.learned())
                .messageToJunior(request.messageToJunior())
                .build();

        Project savedProject = projectSaveService.save(project);

        if (request.recruitments() != null) {
            request.recruitments().forEach(r -> savedProject.addRecruitment(
                    Recruitment.builder()
                            .project(savedProject)
                            .role(r.role())
                            .count(r.count())
                            .skills(r.skills())
                            .description(r.description())
                            .build()
            ));
        }

        if (request.teamMembers() != null) {
            request.teamMembers().forEach(m -> savedProject.addTeamMember(
                    TeamMember.builder()
                            .project(savedProject)
                            .role(m.role())
                            .count(m.count())
                            .build()
            ));
        }

        Project flushedProject = projectSaveService.saveAndFlush(savedProject);
        return ProjectMapper.toResponse(flushedProject, 0, 0, java.util.Map.of(), false, false, false);
    }
}
