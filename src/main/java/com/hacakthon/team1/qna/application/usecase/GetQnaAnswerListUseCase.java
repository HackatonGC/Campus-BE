package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.application.dto.response.QnaAnswerResponse;
import com.hacakthon.team1.qna.application.mapper.QnaAnswerMapper;
import com.hacakthon.team1.qna.domain.service.QnaAnswerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetQnaAnswerListUseCase {

    private final QnaAnswerQueryService qnaAnswerQueryService;

    @Transactional(readOnly = true)
    public List<QnaAnswerResponse> getList(Long qnaId) {
        return qnaAnswerQueryService.findAllByQnaId(qnaId)
                .stream()
                .map(QnaAnswerMapper::toResponse)
                .toList();
    }
}
