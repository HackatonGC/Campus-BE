package com.hacakthon.team1.reviewanswer.domain.repository;

import com.hacakthon.team1.reviewanswer.domain.entity.ReviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewAnswerRepository extends JpaRepository<ReviewAnswer, Long> {

    List<ReviewAnswer> findAllByReviewRequestId(Long reviewRequestId);
}
