package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.user.application.dto.request.UpdatePortfolioRequest;
import com.hacakthon.team1.user.application.dto.response.PortfolioResponse;
import com.hacakthon.team1.user.application.mapper.UserMapper;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import com.hacakthon.team1.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdatePortfolioUseCase {

    private final UserQueryService userQueryService;
    private final UserSaveService userSaveService;

    @Transactional
    public PortfolioResponse update(Long targetId, Long requesterId, UpdatePortfolioRequest request) {
        if (!targetId.equals(requesterId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        User user = userQueryService.findById(targetId);
        user.updatePortfolio(request.bio(), request.githubUrl(), request.blogUrl(),
                request.portfolioUrl(), request.techStacks());
        return UserMapper.toPortfolioResponse(userSaveService.save(user));
    }
}
