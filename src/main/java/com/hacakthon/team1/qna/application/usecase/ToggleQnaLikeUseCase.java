package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.entity.QnaLike;
import com.hacakthon.team1.qna.domain.repository.QnaLikeRepository;
import com.hacakthon.team1.qna.domain.service.QnaQueryService;
import com.hacakthon.team1.qna.domain.service.QnaSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ToggleQnaLikeUseCase {

    private final QnaQueryService qnaQueryService;
    private final QnaSaveService qnaSaveService;
    private final QnaLikeRepository qnaLikeRepository;
    private final UserQueryService userQueryService;

    @Transactional
    public boolean toggle(Long userId, Long qnaId) {
        Qna qna = qnaQueryService.findById(qnaId);
        User user = userQueryService.findById(userId);

        Optional<QnaLike> existing = qnaLikeRepository.findByQnaIdAndUserId(qnaId, userId);
        if (existing.isPresent()) {
            qnaLikeRepository.delete(existing.get());
            qna.decrementLikeCount();
            qnaSaveService.save(qna);
            return false;
        } else {
            qnaLikeRepository.save(QnaLike.builder().qna(qna).user(user).build());
            qna.incrementLikeCount();
            qnaSaveService.save(qna);
            return true;
        }
    }
}
