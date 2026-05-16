package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.user.application.dto.request.UpdatePrivacyRequest;
import com.hacakthon.team1.user.application.dto.response.UserResponse;
import com.hacakthon.team1.user.application.mapper.UserMapper;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import com.hacakthon.team1.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdatePrivacyUseCase {

    private final UserQueryService userQueryService;
    private final UserSaveService userSaveService;

    @Transactional
    public UserResponse updatePrivacy(Long userId, UpdatePrivacyRequest request) {
        User user = userQueryService.findById(userId);
        user.updatePrivacy(request.isProjectPublic(), request.isProfilePublic(), request.isActivityPublic());
        return UserMapper.toResponse(userSaveService.save(user));
    }
}
