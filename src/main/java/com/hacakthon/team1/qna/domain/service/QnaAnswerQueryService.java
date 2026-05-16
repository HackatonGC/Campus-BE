package com.hacakthon.team1.qna.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.qna.domain.entity.QnaAnswer;
import com.hacakthon.team1.qna.domain.repository.QnaAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaAnswerQueryService {

    private final QnaAnswerRepository qnaAnswerRepository;

    public QnaAnswer findById(Long id) {
        return qnaAnswerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.QNA_ANSWER_NOT_FOUND));
    }

    public List<QnaAnswer> findAllByQnaId(Long qnaId) {
        return qnaAnswerRepository.findAllByQnaId(qnaId);
    }
}
