package com.hacakthon.team1.project.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.project.application.dto.request.CreateProjectRequest;
import com.hacakthon.team1.project.application.dto.response.ProjectResponse;
import com.hacakthon.team1.project.application.dto.response.ProjectSummaryResponse;
import com.hacakthon.team1.project.application.dto.request.UpdateProjectRequest;
import com.hacakthon.team1.project.domain.entity.ProjectStatus;
import com.hacakthon.team1.project.application.usecase.CreateProjectUseCase;
import com.hacakthon.team1.project.application.usecase.DeleteProjectUseCase;
import com.hacakthon.team1.project.application.usecase.GetProjectByShareTokenUseCase;
import com.hacakthon.team1.project.application.usecase.GetProjectListUseCase;
import com.hacakthon.team1.project.application.usecase.GetProjectUseCase;
import com.hacakthon.team1.project.application.usecase.UpdateProjectUseCase;
import com.hacakthon.team1.config.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@Tag(name = "Project", description = "프로젝트 관련 API")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;
    private final GetProjectListUseCase getProjectListUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final GetProjectByShareTokenUseCase getProjectByShareTokenUseCase;

    @PostMapping
    @Operation(summary = "프로젝트 등록")
    public ResponseEntity<CommonResponse<ProjectResponse>> create(
            @CurrentUser Long userId,
            @RequestBody CreateProjectRequest request) {
        ProjectResponse response = createProjectUseCase.create(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.PROJECT_CREATED, response));
    }

    @GetMapping
    @Operation(summary = "프로젝트 목록 조회 (검색/필터/정렬)")
    public ResponseEntity<CommonResponse<List<ProjectSummaryResponse>>> getList(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<String> techStacks,
            @RequestParam(required = false) ProjectStatus status,
            @RequestParam(defaultValue = "latest") String sort) {
        List<ProjectSummaryResponse> response = getProjectListUseCase.getList(keyword, techStacks, status, sort);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.PROJECT_LIST_FOUND, response));
    }

    @GetMapping("/popular-tags")
    @Operation(summary = "인기 기술스택 태그 조회")
    public ResponseEntity<CommonResponse<List<String>>> getPopularTags(
            @RequestParam(defaultValue = "5") int limit) {
        List<String> response = getProjectListUseCase.getPopularTags(limit);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.PROJECT_POPULAR_TAGS_FOUND, response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "프로젝트 단건 조회")
    public ResponseEntity<CommonResponse<ProjectResponse>> get(@PathVariable Long id) {
        ProjectResponse response = getProjectUseCase.get(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.PROJECT_FOUND, response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "프로젝트 수정")
    public ResponseEntity<CommonResponse<ProjectResponse>> update(
            @PathVariable Long id,
            @RequestBody UpdateProjectRequest request,
            @CurrentUser Long userId) {
        ProjectResponse response = updateProjectUseCase.update(id, userId, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.PROJECT_UPDATED, response));
    }

    @GetMapping("/share/{shareToken}")
    @Operation(summary = "공유 링크로 프로젝트 조회")
    public ResponseEntity<CommonResponse<ProjectResponse>> getByShareToken(@PathVariable String shareToken) {
        ProjectResponse response = getProjectByShareTokenUseCase.get(shareToken);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.PROJECT_FOUND, response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "프로젝트 삭제")
    public ResponseEntity<CommonResponse<Void>> delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        deleteProjectUseCase.delete(id, userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.PROJECT_DELETED, null));
    }
}
