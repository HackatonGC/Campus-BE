package com.hacakthon.team1.teamapplication.application.usecase;

import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.application.mapper.TeamApplicationMapper;
import com.hacakthon.team1.teamapplication.domain.service.TeamApplicationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTeamApplicationListUseCase {

    private final TeamApplicationQueryService teamApplicationQueryService;

    @Transactional(readOnly = true)
    public List<TeamApplicationResponse> getByProject(Long projectId) {
        return teamApplicationQueryService.findAllByProjectId(projectId)
                .stream()
                .map(TeamApplicationMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TeamApplicationResponse> getByUser(Long userId) {
        return teamApplicationQueryService.findAllByUserId(userId)
                .stream()
                .map(TeamApplicationMapper::toResponse)
                .toList();
    }
}
