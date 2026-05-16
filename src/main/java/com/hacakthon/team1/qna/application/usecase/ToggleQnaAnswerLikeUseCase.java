package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.domain.entity.QnaAnswer;
import com.hacakthon.team1.qna.domain.entity.QnaAnswerLike;
import com.hacakthon.team1.qna.domain.repository.QnaAnswerLikeRepository;
import com.hacakthon.team1.qna.domain.service.QnaAnswerQueryService;
import com.hacakthon.team1.qna.domain.service.QnaAnswerSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ToggleQnaAnswerLikeUseCase {

    private final QnaAnswerQueryService qnaAnswerQueryService;
    private final QnaAnswerSaveService qnaAnswerSaveService;
    private final QnaAnswerLikeRepository qnaAnswerLikeRepository;
    private final UserQueryService userQueryService;

    @Transactional
    public boolean toggle(Long userId, Long answerId) {
        QnaAnswer answer = qnaAnswerQueryService.findById(answerId);
        User user = userQueryService.findById(userId);

        Optional<QnaAnswerLike> existing = qnaAnswerLikeRepository.findByQnaAnswerIdAndUserId(answerId, userId);
        if (existing.isPresent()) {
            qnaAnswerLikeRepository.delete(existing.get());
            answer.decrementLikeCount();
            qnaAnswerSaveService.save(answer);
            return false;
        } else {
            qnaAnswerLikeRepository.save(QnaAnswerLike.builder().qnaAnswer(answer).user(user).build());
            answer.incrementLikeCount();
            qnaAnswerSaveService.save(answer);
            return true;
        }
    }
}
