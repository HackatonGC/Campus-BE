package com.hacakthon.team1.project.domain.repository;

import com.hacakthon.team1.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserId(Long userId);
}
