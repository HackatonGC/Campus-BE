package com.hacakthon.team1.project.domain.repository;

import com.hacakthon.team1.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
