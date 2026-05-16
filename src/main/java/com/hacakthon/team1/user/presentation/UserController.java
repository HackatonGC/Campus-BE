package com.hacakthon.team1.user.presentation;

import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import com.hacakthon.team1.user.application.dto.request.LoginRequest;
import com.hacakthon.team1.user.application.dto.request.SignUpRequest;
import com.hacakthon.team1.user.application.dto.response.LoginResponse;
import com.hacakthon.team1.user.application.dto.response.UserResponse;
import com.hacakthon.team1.user.application.usecase.LoginUseCase;
import com.hacakthon.team1.user.application.usecase.SignUpUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 관련 API")
public class UserController {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;

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
}
