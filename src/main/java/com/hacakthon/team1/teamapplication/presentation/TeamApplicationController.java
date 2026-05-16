package com.hacakthon.team1.teamapplication.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.teamapplication.application.dto.request.TeamApplicationRequest;
import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.application.usecase.ApplyTeamUseCase;
import com.hacakthon.team1.teamapplication.application.usecase.GetTeamApplicationListUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
@Tag(name = "TeamApplication", description = "팀원 신청 관련 API")
public class TeamApplicationController {

    private final ApplyTeamUseCase applyTeamUseCase;
    private final GetTeamApplicationListUseCase getTeamApplicationListUseCase;

    @PostMapping
    @Operation(summary = "팀원 신청", description = "프로젝트에 팀원으로 참여 신청합니다.")
    public ResponseEntity<CommonResponse<Void>> apply(
            @RequestParam Long userId,
            @RequestBody TeamApplicationRequest request
    ) {
        applyTeamUseCase.apply(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.APPLICATION_CREATED));
    }

    @GetMapping("/project/{projectId}")
    @Operation(summary = "프로젝트별 신청 목록 조회", description = "특정 프로젝트에 들어온 팀원 신청 목록을 조회합니다.")
    public ResponseEntity<CommonResponse<List<TeamApplicationResponse>>> getByProject(
            @PathVariable Long projectId
    ) {
        List<TeamApplicationResponse> responses = getTeamApplicationListUseCase.getByProject(projectId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_LIST_FOUND, responses));
    }

    @GetMapping("/my")
    @Operation(summary = "내 신청 목록 조회", description = "내가 신청한 팀원 신청 목록을 조회합니다.")
    public ResponseEntity<CommonResponse<List<TeamApplicationResponse>>> getMyApplications(
            @RequestParam Long userId
    ) {
        List<TeamApplicationResponse> responses = getTeamApplicationListUseCase.getByUser(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_LIST_FOUND, responses));
    }
}
