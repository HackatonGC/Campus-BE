package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.application.dto.request.CreateQnaRequest;
import com.hacakthon.team1.qna.application.dto.response.QnaResponse;
import com.hacakthon.team1.qna.application.mapper.QnaMapper;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.service.QnaSaveService;
import com.hacakthon.team1.user.domain.entity.User;
import com.hacakthon.team1.user.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateQnaUseCase {

    private final QnaSaveService qnaSaveService;
    private final UserQueryService userQueryService;

    @Transactional
    public QnaResponse create(Long userId, CreateQnaRequest request) {
        User author = userQueryService.findById(userId);
        Qna qna = Qna.builder()
                .author(author)
                .title(request.title())
                .content(request.content())
                .tags(request.tags())
                .build();
        qnaSaveService.save(qna);
        return QnaMapper.toResponse(qna);
    }
}
