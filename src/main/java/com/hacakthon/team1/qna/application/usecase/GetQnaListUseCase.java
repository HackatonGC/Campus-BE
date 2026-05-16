package com.hacakthon.team1.qna.application.usecase;

import com.hacakthon.team1.qna.application.dto.response.QnaResponse;
import com.hacakthon.team1.qna.application.mapper.QnaMapper;
import com.hacakthon.team1.qna.domain.entity.Qna;
import com.hacakthon.team1.qna.domain.service.QnaQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetQnaListUseCase {

    private final QnaQueryService qnaQueryService;

    @Transactional(readOnly = true)
    public List<QnaResponse> getList(String keyword, List<String> tags, String sort) {
        List<Qna> qnas = qnaQueryService.findAllByFilter(keyword, tags);

        if ("popular".equals(sort)) {
            qnas = qnas.stream().sorted(Comparator.comparingInt(Qna::getLikeCount).reversed()).toList();
        } else {
            qnas = qnas.stream().sorted(Comparator.comparing(Qna::getCreatedAt).reversed()).toList();
        }

        return qnas.stream().map(QnaMapper::toResponse).toList();
    }
}
