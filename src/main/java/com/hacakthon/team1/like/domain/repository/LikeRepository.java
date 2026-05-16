package com.hacakthon.team1.like.domain.repository;

import com.hacakthon.team1.like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);

    Optional<Like> findByUserIdAndProjectId(Long userId, Long projectId);

    List<Like> findAllByUserId(Long userId);
}
