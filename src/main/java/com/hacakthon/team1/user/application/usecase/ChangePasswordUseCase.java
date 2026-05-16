package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.user.application.dto.request.ChangePasswordRequest;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import com.hacakthon.team1.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangePasswordUseCase {

    private final UserQueryService userQueryService;
    private final UserSaveService userSaveService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(Long userId, ChangePasswordRequest request) {
        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }

        User user = userQueryService.findById(userId);

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        user.updatePassword(passwordEncoder.encode(request.newPassword()));
        userSaveService.save(user);
    }
}
