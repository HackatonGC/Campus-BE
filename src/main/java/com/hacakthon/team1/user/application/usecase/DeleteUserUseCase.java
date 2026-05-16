package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import com.hacakthon.team1.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserQueryService userQueryService;
    private final UserSaveService userSaveService;

    @Transactional
    public void delete(Long targetId, Long requesterId) {
        if (!targetId.equals(requesterId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        User user = userQueryService.findById(targetId);
        userSaveService.delete(user);
    }
}
