package com.hacakthon.team1.qna.application.mapper;

import com.hacakthon.team1.qna.application.dto.response.QnaAnswerResponse;
import com.hacakthon.team1.qna.domain.entity.QnaAnswer;

public class QnaAnswerMapper {

    public static QnaAnswerResponse toResponse(QnaAnswer answer) {
        return new QnaAnswerResponse(
                answer.getId(),
                answer.getQna().getId(),
                answer.getAuthor().getId(),
                answer.getAuthor().getName(),
                answer.getAuthor().getSchool(),
                answer.getContent(),
                answer.isAccepted(),
                answer.getLikeCount(),
                answer.getCreatedAt()
        );
    }
}
