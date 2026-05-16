package com.hacakthon.team1.comment.domain.service;

import com.hacakthon.team1.comment.domain.entity.Comment;
import com.hacakthon.team1.comment.domain.entity.CommentLike;
import com.hacakthon.team1.comment.domain.repository.CommentLikeRepository;
import com.hacakthon.team1.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentSaveService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public void saveLike(CommentLike commentLike) {
        commentLikeRepository.save(commentLike);
    }

    public void deleteLike(CommentLike commentLike) {
        commentLikeRepository.delete(commentLike);
    }
}
