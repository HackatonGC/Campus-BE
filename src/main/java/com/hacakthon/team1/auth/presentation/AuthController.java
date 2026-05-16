package com.hacakthon.team1.auth.presentation;

import com.hacakthon.team1.auth.application.dto.request.EmailSendRequest;
import com.hacakthon.team1.auth.application.dto.request.EmailVerifyRequest;
import com.hacakthon.team1.auth.application.service.EmailVerificationService;
import com.hacakthon.team1.common.response.CommonResponse;
import com.hacakthon.team1.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/email")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "이메일 인증 관련 API")
public class AuthController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/send")
    @Operation(summary = "이메일 인증 코드 발송", description = "입력한 이메일로 6자리 인증 코드를 발송합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 코드 발송 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 이메일 형식")
    })
    public ResponseEntity<CommonResponse<Void>> sendCode(
            @Valid @RequestBody EmailSendRequest request
    ) {
        emailVerificationService.sendCode(request.email());
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.EMAIL_CODE_SENT));
    }

    @PostMapping("/verify")
    @Operation(summary = "이메일 인증 코드 검증", description = "발송된 인증 코드를 검증합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 성공"),
            @ApiResponse(responseCode = "400", description = "코드 불일치 또는 만료")
    })
    public ResponseEntity<CommonResponse<Void>> verifyCode(
            @Valid @RequestBody EmailVerifyRequest request
    ) {
        emailVerificationService.verifyCode(request.email(), request.code());
        return ResponseEntity.ok(CommonResponse.success(ResponseMessage.EMAIL_CODE_VERIFIED));
    }
}
