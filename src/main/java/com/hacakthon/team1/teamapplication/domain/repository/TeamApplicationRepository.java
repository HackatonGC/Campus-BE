package com.hacakthon.team1.teamapplication.domain.repository;

import com.hacakthon.team1.teamapplication.domain.entity.TeamApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamApplicationRepository extends JpaRepository<TeamApplication, Long> {

    List<TeamApplication> findAllByProjectId(Long projectId);

    List<TeamApplication> findAllByUserId(Long userId);

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
}
