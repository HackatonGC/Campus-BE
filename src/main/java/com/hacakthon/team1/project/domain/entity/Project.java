package com.hacakthon.team1.project.domain.entity;

import com.hacakthon.team1.domain.entity.BaseEntity;
import com.hacakthon.team1.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "project_tech_stacks", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "tech_stack")
    private List<String> techStacks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectType projectType;

    private String thumbnailUrl;
    private String githubUrl;
    private String deployUrl;
    private String figmaUrl;
    private String notionUrl;

    @Column(nullable = false)
    private int viewCount = 0;

    @Column(nullable = false)
    private int likeCount = 0;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recruitment> recruitments = new ArrayList<>();

    @Builder
    public Project(User user, String title, String summary, String description,
                   List<String> techStacks, ProjectStatus status, ProjectType projectType,
                   String thumbnailUrl, String githubUrl, String deployUrl,
                   String figmaUrl, String notionUrl) {
        this.user = user;
        this.title = title;
        this.summary = summary;
        this.description = description;
        if (techStacks != null) this.techStacks = techStacks;
        this.status = status;
        this.projectType = projectType;
        this.thumbnailUrl = thumbnailUrl;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
        this.figmaUrl = figmaUrl;
        this.notionUrl = notionUrl;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) this.likeCount--;
    }

    public void addRecruitment(Recruitment recruitment) {
        this.recruitments.add(recruitment);
    }
}
