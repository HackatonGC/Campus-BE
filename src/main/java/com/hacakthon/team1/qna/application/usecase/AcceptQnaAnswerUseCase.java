package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.entity.QnaAnswer;
import com.hacakthon.team1.qna.domain.service.QnaAnswerQueryService;
import com.hacakthon.team1.qna.domain.service.QnaAnswerSaveService;
import com.hacakthon.team1.qna.domain.service.QnaQueryService;
import com.hacakthon.team1.qna.domain.service.QnaSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AcceptQnaAnswerUseCase {

    private final QnaAnswerQueryService qnaAnswerQueryService;
    private final QnaAnswerSaveService qnaAnswerSaveService;
    private final QnaQueryService qnaQueryService;
    private final QnaSaveService qnaSaveService;

    @Transactional
    public void accept(Long userId, Long qnaId, Long answerId) {
        Qna qna = qnaQueryService.findById(qnaId);
        if (!qna.getAuthor().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.QNA_ACCESS_DENIED);
        }
        if (qna.isSolved()) {
            throw new BusinessException(ErrorCode.QNA_ALREADY_SOLVED);
        }

        QnaAnswer answer = qnaAnswerQueryService.findById(answerId);
        answer.accept();
        qnaAnswerSaveService.save(answer);

        qna.markSolved();
        qnaSaveService.save(qna);
    }
}
