package com.hacakthon.team1.project.domain.entity;

import com.hacakthon.team1.domain.entity.BaseEntity;
import com.hacakthon.team1.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    // 모집 관련 필드
    private String duration;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    private LocalDate deadline;

    @Column(columnDefinition = "TEXT")
    private String recruitMessage;

    // 완료 관련 필드
    private LocalDate startDate;
    private LocalDate endDate;
    private String myRole;

    @Column(columnDefinition = "TEXT")
    private String features;

    @ElementCollection
    @CollectionTable(name = "project_demo_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "demo_image_url")
    private List<String> demoImages = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String hardPart;

    @Column(columnDefinition = "TEXT")
    private String learned;

    @Column(columnDefinition = "TEXT")
    private String messageToJunior;

    @Column(nullable = false, unique = true, updatable = false)
    private String shareToken;

    @Column(nullable = false)
    private int viewCount = 0;

    @Column(nullable = false)
    private int likeCount = 0;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recruitment> recruitments = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMembers = new ArrayList<>();

    @Builder
    public Project(User user, String title, String summary, String description,
                   List<String> techStacks, ProjectStatus status, ProjectType projectType,
                   String thumbnailUrl, String githubUrl, String deployUrl,
                   String figmaUrl, String notionUrl,
                   String duration, MeetingType meetingType, LocalDate deadline,
                   String recruitMessage,
                   LocalDate startDate, LocalDate endDate, String myRole, String features,
                   List<String> demoImages, String hardPart, String learned, String messageToJunior) {
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
        this.duration = duration;
        this.meetingType = meetingType;
        this.deadline = deadline;
        this.recruitMessage = recruitMessage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.myRole = myRole;
        this.features = features;
        if (demoImages != null) this.demoImages = demoImages;
        this.hardPart = hardPart;
        this.learned = learned;
        this.messageToJunior = messageToJunior;
        this.shareToken = UUID.randomUUID().toString();
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

    public void update(String title, String summary, String description, List<String> techStacks,
                       ProjectStatus status, ProjectType projectType, String thumbnailUrl,
                       String githubUrl, String deployUrl, String figmaUrl, String notionUrl,
                       String duration, MeetingType meetingType, LocalDate deadline,
                       String recruitMessage,
                       LocalDate startDate, LocalDate endDate, String myRole, String features,
                       List<String> demoImages, String hardPart, String learned, String messageToJunior) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.techStacks = techStacks != null ? techStacks : new ArrayList<>();
        this.status = status;
        this.projectType = projectType;
        this.thumbnailUrl = thumbnailUrl;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
        this.figmaUrl = figmaUrl;
        this.notionUrl = notionUrl;
        this.duration = duration;
        this.meetingType = meetingType;
        this.deadline = deadline;
        this.recruitMessage = recruitMessage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.myRole = myRole;
        this.features = features;
        this.demoImages = demoImages != null ? demoImages : new ArrayList<>();
        this.hardPart = hardPart;
        this.learned = learned;
        this.messageToJunior = messageToJunior;
    }

    public void updateStatus(ProjectStatus status) {
        this.status = status;
    }

    public void clearRecruitments() {
        this.recruitments.clear();
    }

    public void addRecruitment(Recruitment recruitment) {
        this.recruitments.add(recruitment);
    }

    public void clearTeamMembers() {
        this.teamMembers.clear();
    }

    public void addTeamMember(TeamMember teamMember) {
        this.teamMembers.add(teamMember);
    }
}
