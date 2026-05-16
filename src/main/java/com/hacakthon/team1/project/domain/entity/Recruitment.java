package com.hacakthon.team1.project.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruitments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private int count;

    private String description;

    @Builder
    public Recruitment(Project project, String role, int count, String description) {
        this.project = project;
        this.role = role;
        this.count = count;
        this.description = description;
    }
}
