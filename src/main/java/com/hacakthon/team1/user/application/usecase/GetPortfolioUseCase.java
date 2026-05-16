package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.user.application.dto.response.PortfolioResponse;
import com.hacakthon.team1.user.application.mapper.UserMapper;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetPortfolioUseCase {

    private final UserQueryService userQueryService;

    @Transactional(readOnly = true)
    public PortfolioResponse get(Long userId) {
        return UserMapper.toPortfolioResponse(userQueryService.findById(userId));
    }
}
