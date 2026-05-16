package com.hacakthon.team1.qna.domain.service;

import com.hacakthon.team1.qna.domain.entity.QnaAnswer;
import com.hacakthon.team1.qna.domain.repository.QnaAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaAnswerSaveService {

    private final QnaAnswerRepository qnaAnswerRepository;

    public void save(QnaAnswer qnaAnswer) {
        qnaAnswerRepository.save(qnaAnswer);
    }

    public void delete(QnaAnswer qnaAnswer) {
        qnaAnswerRepository.delete(qnaAnswer);
    }
}
