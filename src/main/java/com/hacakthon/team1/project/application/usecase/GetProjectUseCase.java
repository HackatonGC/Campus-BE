package com.hacakthon.team1.project.application.usecase;

import com.hacakthon.team1.comment.domain.repository.CommentRepository;
import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.mapper.ProjectMapper;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.service.ProjectQueryService;
import com.hacakthon.team1.project.domain.service.ProjectSaveService;
import com.hacakthon.team1.bookmark.domain.repository.BookmarkRepository;
import com.hacakthon.team1.like.domain.repository.LikeRepository;
import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetProjectUseCase {

    private final ProjectQueryService projectQueryService;
    private final ProjectSaveService projectSaveService;
    private final CommentRepository commentRepository;
    private final TeamApplicationRepository teamApplicationRepository;
    private final LikeRepository likeRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public ProjectResponse get(Long id, Long userId) {
        Project project = projectQueryService.findById(id);
        project.incrementViewCount();
        projectSaveService.save(project);

        long commentCount = commentRepository.countByProjectId(id);
        long totalApplicationCount = teamApplicationRepository.countByProjectId(id);
        Map<Long, Long> acceptedCountMap = buildAcceptedCountMap(project);
        boolean isApplied = userId != null && teamApplicationRepository.existsByUserIdAndProjectId(userId, id);
        boolean isLiked = userId != null && likeRepository.existsByUserIdAndProjectId(userId, id);
        boolean isBookmarked = userId != null && bookmarkRepository.existsByUserIdAndProjectId(userId, id);

        return ProjectMapper.toResponse(project, commentCount, totalApplicationCount, acceptedCountMap, isApplied, isLiked, isBookmarked);
    }

    private Map<Long, Long> buildAcceptedCountMap(Project project) {
        return project.getRecruitments().stream()
                .collect(Collectors.toMap(
                        r -> r.getId(),
                        r -> teamApplicationRepository.countByRecruitmentIdAndStatus(r.getId(), ApplicationStatus.ACCEPTED)
                ));
    }
}
