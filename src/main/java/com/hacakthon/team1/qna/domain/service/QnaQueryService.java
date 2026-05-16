package com.hacakthon.team1.qna.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaQueryService {

    private final QnaRepository qnaRepository;

    public Qna findById(Long id) {
        return qnaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.QNA_NOT_FOUND));
    }

    public List<Qna> findAllByFilter(String keyword, List<String> tags) {
        return qnaRepository.findAllByFilter(keyword, tags);
    }
}
