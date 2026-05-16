package com.hacakthon.team1.qna.domain.repository;

import com.hacakthon.team1.qna.domain.entity.QnaLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaLikeRepository extends JpaRepository<QnaLike, Long> {

    Optional<QnaLike> findByQnaIdAndUserId(Long qnaId, Long userId);
}
