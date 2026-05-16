package com.hacakthon.team1.teamapplication.domain.repository;

import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamApplicationRepository extends JpaRepository<TeamApplication, Long> {

    List<TeamApplication> findAllByProjectId(Long projectId);

    List<TeamApplication> findAllByUserId(Long userId);

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);

    long countByProjectId(Long projectId);

    long countByUserIdAndStatus(Long userId, com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus status);

    long countByProjectIdAndStatus(Long projectId, com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus status);

    long countByRecruitmentIdAndStatus(Long recruitmentId, com.hacakthon.team1.teamapplication.domain.entity.ApplicationStatus status);
}
