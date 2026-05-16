package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.application.dto.request.CreateQnaAnswerRequest;
import com.hacakthon.team1.qna.application.dto.response.QnaAnswerResponse;
import com.hacakthon.team1.qna.application.mapper.QnaAnswerMapper;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.entity.QnaAnswer;
import com.hacakthon.team1.qna.domain.service.QnaAnswerSaveService;
import com.hacakthon.team1.qna.domain.service.QnaQueryService;
import com.hacakthon.team1.qna.domain.service.QnaSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateQnaAnswerUseCase {

    private final QnaAnswerSaveService qnaAnswerSaveService;
    private final QnaQueryService qnaQueryService;
    private final QnaSaveService qnaSaveService;
    private final UserQueryService userQueryService;

    @Transactional
    public QnaAnswerResponse create(Long userId, Long qnaId, CreateQnaAnswerRequest request) {
        User author = userQueryService.findById(userId);
        Qna qna = qnaQueryService.findById(qnaId);

        QnaAnswer answer = QnaAnswer.builder()
                .qna(qna)
                .author(author)
                .content(request.content())
                .build();
        qnaAnswerSaveService.save(answer);

        qna.incrementAnswerCount();
        qnaSaveService.save(qna);

        return QnaAnswerMapper.toResponse(answer);
    }
}
