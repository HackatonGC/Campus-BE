package com.hacakthon.team1.teamapplication.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.teamapplication.application.dto.request.TeamApplicationRequest;
import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.application.usecase.ApplyTeamUseCase;
import com.hacakthon.team1.teamapplication.application.usecase.GetTeamApplicationListUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "TeamApplication", description = "팀원 신청 관련 API")
public class TeamApplicationController {

    private final ApplyTeamUseCase applyTeamUseCase;
    private final GetTeamApplicationListUseCase getTeamApplicationListUseCase;

    @PostMapping("/api/v1/projects/{projectId}/applications")
    @Operation(summary = "팀원 신청", description = "프로젝트에 팀원으로 참여 신청합니다. 중복 신청 및 모집 종료 프로젝트에는 신청할 수 없습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "신청 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력 또는 모집 중이 아닌 프로젝트"),
            @ApiResponse(responseCode = "404", description = "프로젝트 또는 유저 없음"),
            @ApiResponse(responseCode = "409", description = "중복 신청")
    })
    public ResponseEntity<CommonResponse<Void>> apply(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @Parameter(description = "유저 ID (임시 - JWT 연동 전)") @RequestParam Long userId,
            @Valid @RequestBody TeamApplicationRequest request
    ) {
        applyTeamUseCase.apply(userId, projectId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.APPLICATION_CREATED));
    }

    @GetMapping("/api/v1/projects/{projectId}/applications")
    @Operation(summary = "프로젝트별 신청 목록 조회", description = "특정 프로젝트에 들어온 팀원 신청 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "프로젝트 없음")
    })
    public ResponseEntity<CommonResponse<List<TeamApplicationResponse>>> getByProject(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId
    ) {
        List<TeamApplicationResponse> responses = getTeamApplicationListUseCase.getByProject(projectId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_LIST_FOUND, responses));
    }

    @GetMapping("/api/v1/applications/my")
    @Operation(summary = "내 신청 목록 조회", description = "내가 신청한 팀원 신청 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저 없음")
    })
    public ResponseEntity<CommonResponse<List<TeamApplicationResponse>>> getMyApplications(
            @Parameter(description = "유저 ID (임시 - JWT 연동 전)") @RequestParam Long userId
    ) {
        List<TeamApplicationResponse> responses = getTeamApplicationListUseCase.getByUser(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_LIST_FOUND, responses));
    }
}
