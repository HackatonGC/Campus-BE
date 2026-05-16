package com.hacakthon.team1.qna.domain.entity;

import com.hacakthon.team1.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qna_answer_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"qna_answer_id", "user_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaAnswerLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_answer_id", nullable = false)
    private QnaAnswer qnaAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public QnaAnswerLike(QnaAnswer qnaAnswer, User user) {
        this.qnaAnswer = qnaAnswer;
        this.user = user;
    }
}
