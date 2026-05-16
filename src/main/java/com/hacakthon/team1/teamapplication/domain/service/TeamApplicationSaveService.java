package com.hacakthon.team1.teamapplication.domain.service;

import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamApplicationSaveService {

    private final TeamApplicationRepository teamApplicationRepository;

    public TeamApplication save(TeamApplication teamApplication) {
        return teamApplicationRepository.save(teamApplication);
    }

    public void delete(TeamApplication teamApplication) {
        teamApplicationRepository.delete(teamApplication);
    }

    public boolean existsByUserIdAndProjectId(Long userId, Long projectId) {
        return teamApplicationRepository.existsByUserIdAndProjectId(userId, projectId);
    }
}
