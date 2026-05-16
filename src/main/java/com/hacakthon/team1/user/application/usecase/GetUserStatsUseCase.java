package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.project.domain.repository.ProjectRepository;
import com.hacakthon.team1.reviewrequest.domain.repository.ReviewRequestRepository;
import com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import com.hacakthon.team1.user.application.dto.response.UserStatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetUserStatsUseCase {

    private final ProjectRepository projectRepository;
    private final ReviewRequestRepository reviewRequestRepository;
    private final TeamApplicationRepository teamApplicationRepository;

    @Transactional(readOnly = true)
    public UserStatsResponse getStats(Long userId) {
        long projectCount = projectRepository.findAllByUserId(userId).size();

        long totalLikeCount = projectRepository.findAllByUserId(userId).stream()
                .mapToLong(p -> p.getLikeCount())
                .sum();

        long codeReviewCount = reviewRequestRepository.findAllByRequesterId(userId).size();

        long teamParticipationCount = teamApplicationRepository
                .countByUserIdAndStatus(userId, ApplicationStatus.ACCEPTED);

        return new UserStatsResponse(projectCount, totalLikeCount, codeReviewCount, teamParticipationCount);
    }
}
