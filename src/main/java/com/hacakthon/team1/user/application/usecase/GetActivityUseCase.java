package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.comment.domain.repository.CommentRepository;
import com.hacakthon.team1.project.domain.repository.ProjectRepository;
import com.hacakthon.team1.reviewrequest.domain.repository.ReviewRequestRepository;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import com.hacakthon.team1.user.application.dto.response.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetActivityUseCase {

    private final ProjectRepository projectRepository;
    private final CommentRepository commentRepository;
    private final TeamApplicationRepository teamApplicationRepository;
    private final ReviewRequestRepository reviewRequestRepository;

    @Transactional(readOnly = true)
    public List<ActivityResponse> getActivities(Long userId) {
        Map<Long, String> projectTitleMap = projectRepository.findAll().stream()
                .collect(Collectors.toMap(p -> p.getId(), p -> p.getTitle()));

        List<ActivityResponse> activities = new ArrayList<>();

        projectRepository.findAllByUserId(userId).forEach(p ->
                activities.add(new ActivityResponse(
                        "PROJECT",
                        "'" + p.getTitle() + "' 프로젝트를 등록했습니다.",
                        p.getCreatedAt()
                ))
        );

        commentRepository.findAllByUserId(userId).forEach(c -> {
            String projectTitle = projectTitleMap.getOrDefault(c.getProjectId(), "알 수 없는 프로젝트");
            activities.add(new ActivityResponse(
                    "COMMENT",
                    "'" + projectTitle + "'에 댓글을 작성했습니다.",
                    c.getCreatedAt()
            ));
        });

        teamApplicationRepository.findAllByUserId(userId).forEach(a -> {
            String projectTitle = projectTitleMap.getOrDefault(a.getProjectId(), "알 수 없는 프로젝트");
            activities.add(new ActivityResponse(
                    "APPLICATION",
                    "'" + projectTitle + "' 팀에 지원했습니다.",
                    a.getCreatedAt()
            ));
        });

        reviewRequestRepository.findAllByRequesterId(userId).forEach(r ->
                activities.add(new ActivityResponse(
                        "REVIEW_REQUEST",
                        "'" + r.getTitle() + "' 코드 리뷰를 요청했습니다.",
                        r.getCreatedAt()
                ))
        );

        return activities.stream()
                .sorted(Comparator.comparing(ActivityResponse::createdAt).reversed())
                .toList();
    }
}
