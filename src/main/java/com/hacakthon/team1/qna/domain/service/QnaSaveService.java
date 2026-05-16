package com.hacakthon.team1.qna.domain.service;

import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaSaveService {

    private final QnaRepository qnaRepository;

    public void save(Qna qna) {
        qnaRepository.save(qna);
    }

    public void delete(Qna qna) {
        qnaRepository.delete(qna);
    }
}
