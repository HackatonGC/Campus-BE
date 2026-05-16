package com.hacakthon.team1.qna.domain.repository;

import com.hacakthon.team1.qna.domain.entity.QnaAnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaAnswerLikeRepository extends JpaRepository<QnaAnswerLike, Long> {

    Optional<QnaAnswerLike> findByQnaAnswerIdAndUserId(Long qnaAnswerId, Long userId);
}
