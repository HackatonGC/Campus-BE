package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.application.dto.response.QnaResponse;
import com.hacakthon.team1.qna.application.mapper.QnaMapper;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.service.QnaQueryService;
import com.hacakthon.team1.qna.domain.service.QnaSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetQnaUseCase {

    private final QnaQueryService qnaQueryService;
    private final QnaSaveService qnaSaveService;

    @Transactional
    public QnaResponse get(Long id) {
        Qna qna = qnaQueryService.findById(id);
        qna.incrementViewCount();
        qnaSaveService.save(qna);
        return QnaMapper.toResponse(qna);
    }
}
