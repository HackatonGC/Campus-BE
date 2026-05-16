package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.user.application.dto.request.UpdateUserRequest;
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
public class UpdateUserUseCase {

    private final UserQueryService userQueryService;
    private final UserSaveService userSaveService;

    @Transactional
    public UserResponse update(Long targetId, Long requesterId, UpdateUserRequest request) {
        if (!targetId.equals(requesterId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        User user = userQueryService.findById(targetId);
        user.update(request.name(), request.school(), request.department(), request.techStacks());
        return UserMapper.toResponse(userSaveService.save(user));
    }
}
