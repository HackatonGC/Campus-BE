package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.user.application.dto.request.SignUpRequest;
import com.hacakthon.team1.user.application.dto.response.UserResponse;
import com.hacakthon.team1.user.application.mapper.UserMapper;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SignUpUseCase {

    private final UserSaveService userSaveService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signUp(SignUpRequest request) {
        if (userSaveService.existsByEmail(request.email())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .school(request.school())
                .department(request.department())
                .techStacks(request.techStacks())
                .build();

        return UserMapper.toResponse(userSaveService.save(user));
    }
}
