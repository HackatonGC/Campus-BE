package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.service.QnaQueryService;
import com.hacakthon.team1.qna.domain.service.QnaSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteQnaUseCase {

    private final QnaQueryService qnaQueryService;
    private final QnaSaveService qnaSaveService;

    @Transactional
    public void delete(Long userId, Long qnaId) {
        Qna qna = qnaQueryService.findById(qnaId);
        if (!qna.getAuthor().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.QNA_ACCESS_DENIED);
        }
        qnaSaveService.delete(qna);
    }
}
