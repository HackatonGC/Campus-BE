package com.hacakthon.team1.teamapplication.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import com.hacakthon.team1.teamapplication.domain.repository.TeamApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamApplicationQueryService {

    private final TeamApplicationRepository teamApplicationRepository;

    public TeamApplication findById(Long id) {
        return teamApplicationRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.APPLICATION_NOT_FOUND));
    }

    public List<TeamApplication> findAllByProjectId(Long projectId) {
        return teamApplicationRepository.findAllByProjectId(projectId);
    }

    public List<TeamApplication> findAllByUserId(Long userId) {
        return teamApplicationRepository.findAllByUserId(userId);
    }
}
