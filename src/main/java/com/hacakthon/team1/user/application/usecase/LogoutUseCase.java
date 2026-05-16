package com.hacakthon.team1.user.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.user.domain.entity.TokenBlacklist;
import com.hacakthon.team1.user.domain.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LogoutUseCase {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Value("${jwt.expiration}")
    private long expiration;

    @Transactional
    public void logout(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);

        TokenBlacklist blacklisted = TokenBlacklist.builder()
                .token(token)
                .expiredAt(LocalDateTime.now().plusSeconds(expiration / 1000))
                .build();

        tokenBlacklistRepository.save(blacklisted);
    }
}
