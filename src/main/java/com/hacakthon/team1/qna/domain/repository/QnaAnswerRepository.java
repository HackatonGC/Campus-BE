package com.hacakthon.team1.qna.domain.repository;

import com.hacakthon.team1.qna.domain.entity.QnaAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaAnswerRepository extends JpaRepository<QnaAnswer, Long> {

    List<QnaAnswer> findAllByQnaId(Long qnaId);
}
