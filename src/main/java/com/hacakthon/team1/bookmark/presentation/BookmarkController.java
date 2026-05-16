package com.hacakthon.team1.bookmark.presentation;

import com.hacakthon.team1.bookmark.application.dto.response.BookmarkResponse;
import com.hacakthon.team1.bookmark.application.usecase.AddBookmarkUseCase;
import com.hacakthon.team1.bookmark.application.usecase.GetBookmarkListUseCase;
import com.hacakthon.team1.bookmark.application.usecase.RemoveBookmarkUseCase;
import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Bookmark", description = "북마크 관련 API")
public class BookmarkController {

    private final AddBookmarkUseCase addBookmarkUseCase;
    private final RemoveBookmarkUseCase removeBookmarkUseCase;
    private final GetBookmarkListUseCase getBookmarkListUseCase;

    @PostMapping("/api/v1/projects/{projectId}/bookmarks")
    @Operation(summary = "북마크 추가", description = "프로젝트를 북마크에 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "북마크 추가 완료"),
            @ApiResponse(responseCode = "404", description = "프로젝트 또는 유저 없음"),
            @ApiResponse(responseCode = "409", description = "이미 북마크한 프로젝트")
    })
    public ResponseEntity<CommonResponse<Void>> addBookmark(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @CurrentUser Long userId
    ) {
        addBookmarkUseCase.addBookmark(userId, projectId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.BOOKMARK_ADDED));
    }

    @DeleteMapping("/api/v1/projects/{projectId}/bookmarks")
    @Operation(summary = "북마크 삭제", description = "프로젝트 북마크를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "북마크 삭제 완료"),
            @ApiResponse(responseCode = "404", description = "북마크 없음")
    })
    public ResponseEntity<CommonResponse<Void>> removeBookmark(
            @Parameter(description = "프로젝트 ID") @PathVariable Long projectId,
            @CurrentUser Long userId
    ) {
        removeBookmarkUseCase.removeBookmark(userId, projectId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.BOOKMARK_REMOVED));
    }

    @GetMapping("/api/v1/users/me/bookmarks")
    @Operation(summary = "내 북마크 목록 조회", description = "내가 북마크한 프로젝트 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<CommonResponse<List<BookmarkResponse>>> getBookmarks(
            @CurrentUser Long userId
    ) {
        List<BookmarkResponse> responses = getBookmarkListUseCase.getBookmarks(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.BOOKMARK_LIST_FOUND, responses));
    }
}
