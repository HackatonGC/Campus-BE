package com.hacakthon.team1.user.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.config.CurrentUser;
import com.hacakthon.team1.user.application.dto.request.ChangePasswordRequest;
import com.hacakthon.team1.user.application.dto.request.LoginRequest;
import com.hacakthon.team1.user.application.dto.request.SignUpRequest;
import com.hacakthon.team1.user.application.dto.request.UpdatePrivacyRequest;
import com.hacakthon.team1.user.application.dto.request.UpdateUserRequest;
import com.hacakthon.team1.user.application.dto.response.LoginResponse;
import com.hacakthon.team1.user.application.dto.response.UserResponse;
import com.hacakthon.team1.user.application.usecase.ChangePasswordUseCase;
import com.hacakthon.team1.user.application.usecase.DeleteUserUseCase;
import com.hacakthon.team1.user.application.usecase.GetUserUseCase;
import com.hacakthon.team1.user.application.usecase.LoginUseCase;
import com.hacakthon.team1.user.application.usecase.LogoutUseCase;
import com.hacakthon.team1.user.application.usecase.SignUpUseCase;
import com.hacakthon.team1.user.application.usecase.UpdatePrivacyUseCase;
import com.hacakthon.team1.user.application.usecase.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 관련 API")
public class UserController {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final UpdatePrivacyUseCase updatePrivacyUseCase;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ResponseEntity<CommonResponse<UserResponse>> signUp(@RequestBody SignUpRequest request) {
        UserResponse response = signUpUseCase.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(ResponseMessage.USER_SIGNUP, response));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = loginUseCase.login(request);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_LOGIN, response));
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public ResponseEntity<CommonResponse<Void>> logout(
            @RequestHeader("Authorization") String authorization) {
        logoutUseCase.logout(authorization);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_LOGOUT, null));
    }

    @GetMapping("/{id}")
    @Operation(summary = "유저 정보 조회")
    public ResponseEntity<CommonResponse<UserResponse>> getUser(@PathVariable Long id) {
        UserResponse response = getUserUseCase.get(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_FOUND, response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "프로필 수정")
    public ResponseEntity<CommonResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @CurrentUser Long currentUserId,
            @RequestBody UpdateUserRequest request) {
        UserResponse response = updateUserUseCase.update(id, currentUserId, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_UPDATED, response));
    }

    @PatchMapping("/{id}/password")
    @Operation(summary = "비밀번호 변경")
    public ResponseEntity<CommonResponse<Void>> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {
        changePasswordUseCase.changePassword(id, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_PASSWORD_CHANGED, null));
    }

    @PutMapping("/{id}/privacy")
    @Operation(summary = "공개 범위 설정")
    public ResponseEntity<CommonResponse<UserResponse>> updatePrivacy(
            @PathVariable Long id,
            @RequestBody UpdatePrivacyRequest request) {
        UserResponse response = updatePrivacyUseCase.updatePrivacy(id, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_PRIVACY_UPDATED, response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회원탈퇴")
    public ResponseEntity<CommonResponse<Void>> deleteUser(
            @PathVariable Long id,
            @CurrentUser Long currentUserId) {
        deleteUserUseCase.delete(id, currentUserId);
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.USER_DELETED, null));
    }
}
