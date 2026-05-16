package com.hacakthon.team1.qna.domain.repository;

import com.hacakthon.team1.qna.domain.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Query("SELECT DISTINCT q FROM Qna q LEFT JOIN q.tags t " +
            "WHERE (:keyword IS NULL OR q.title LIKE %:keyword% OR q.content LIKE %:keyword%) " +
            "AND (:tags IS NULL OR t IN :tags)")
    List<Qna> findAllByFilter(@Param("keyword") String keyword, @Param("tags") List<String> tags);
}
