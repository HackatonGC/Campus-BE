package com.hacakthon.team1.teamapplication.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import com.hacakthon.team1.teamapplication.application.dto.request.TeamApplicationRequest;
import com.hacakthon.team1.teamapplication.application.dto.request.UpdateApplicationStatusRequest;
import com.hacakthon.team1.teamapplication.application.dto.response.TeamApplicationResponse;
import com.hacakthon.team1.teamapplication.application.usecase.ApplyTeamUseCase;
import com.hacakthon.team1.teamapplication.application.usecase.CancelApplicationUseCase;
import com.hacakthon.team1.teamapplication.application.usecase.GetTeamApplicationListUseCase;
import com.hacakthon.team1.teamapplication.application.usecase.UpdateApplicationStatusUseCase;
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
    private final UpdateApplicationStatusUseCase updateApplicationStatusUseCase;
    private final CancelApplicationUseCase cancelApplicationUseCase;

    @PostMapping("/api/v1/projects/{projectId}/applications")
    @Operation(summary = "팀원 신청", description = "프로젝트에 팀원으로 참여 신청합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "신청 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력 또는 모집 중이 아닌 프로젝트"),
            @ApiResponse(responseCode = "404", description = "프로젝트 또는 유저 없음"),
            @ApiResponse(responseCode = "409", description = "중복 신청")
    })
    public ResponseEntity<CommonResponse<Void>> apply(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @CurrentUser Long userId,
            @Valid @RequestBody TeamApplicationRequest request
    ) {
        applyTeamUseCase.apply(userId, projectId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.APPLICATION_CREATED));
    }

    @PatchMapping("/api/v1/projects/{projectId}/applications/{applicationId}/status")
    @Operation(summary = "신청 상태 변경", description = "팀원 신청을 수락(ACCEPTED) 또는 거절(REJECTED) 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상태 변경 완료"),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 상태값"),
            @ApiResponse(responseCode = "404", description = "신청 없음"),
            @ApiResponse(responseCode = "409", description = "이미 처리된 신청")
    })
    public ResponseEntity<CommonResponse<Void>> updateStatus(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @Parameter(description = "신청 ID") @PathVariable Long applicationId,
            @Valid @RequestBody UpdateApplicationStatusRequest request
    ) {
        updateApplicationStatusUseCase.updateStatus(applicationId, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_STATUS_UPDATED));
    }

    @DeleteMapping("/api/v1/projects/{projectId}/applications/{applicationId}")
    @Operation(summary = "팀원 신청 취소", description = "본인이 신청한 팀원 신청을 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "취소 완료"),
            @ApiResponse(responseCode = "403", description = "본인 신청이 아님"),
            @ApiResponse(responseCode = "404", description = "신청 없음")
    })
    public ResponseEntity<CommonResponse<Void>> cancel(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @Parameter(description = "신청 ID") @PathVariable Long applicationId,
            @CurrentUser Long userId
    ) {
        cancelApplicationUseCase.cancel(userId, applicationId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_CANCELED));
    }

    @GetMapping("/api/v1/projects/{projectId}/applications")
    @Operation(summary = "프로젝트별 신청 목록 조회", description = "특정 프로젝트에 들어온 팀원 신청 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
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
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<CommonResponse<List<TeamApplicationResponse>>> getMyApplications(
            @CurrentUser Long userId
    ) {
        List<TeamApplicationResponse> responses = getTeamApplicationListUseCase.getByUser(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.APPLICATION_LIST_FOUND, responses));
    }
}
