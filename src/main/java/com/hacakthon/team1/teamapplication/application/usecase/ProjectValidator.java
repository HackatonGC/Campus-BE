package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.teamapplication.application.exception.ProjectNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ProjectValidator {

    public void validateProjectExists(Long projectId) {
        if (projectId == null || projectId <= 0) {
            throw new ProjectNotFoundException();
        }
    }

    public void validateProjectRecruiting(Long projectId) {
        // TODO: Project Entity 연동 후 실제 모집 상태 검증으로 교체
    }
}
