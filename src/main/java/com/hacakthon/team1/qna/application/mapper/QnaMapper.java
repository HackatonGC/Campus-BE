package com.hacakthon.team1.qna.application.mapper;

import com.hacakthon.team1.qna.application.dto.response.QnaResponse;
import com.hacakthon.team1.qna.domain.entity.Qna;

public class QnaMapper {

    public static QnaResponse toResponse(Qna qna) {
        return new QnaResponse(
                qna.getId(),
                qna.getTitle(),
                qna.getContent(),
                qna.getTags(),
                qna.getAuthor().getId(),
                qna.getAuthor().getName(),
                qna.getAuthor().getSchool(),
                qna.isSolved(),
                qna.getAnswerCount(),
                qna.getLikeCount(),
                qna.getViewCount(),
                qna.getCreatedAt()
        );
    }
}
