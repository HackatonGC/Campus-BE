package com.hacakthon.team1.user.domain.entity;

import com.hacakthon.team1.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String school;

    @Column(nullable = false)
    private String department;

    @ElementCollection
    @CollectionTable(name = "user_tech_stacks", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "tech_stack")
    private List<String> techStacks = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String githubUrl;
    private String blogUrl;
    private String portfolioUrl;

    @Column(nullable = false)
    private boolean isProjectPublic = true;

    @Column(nullable = false)
    private boolean isProfilePublic = true;

    @Column(nullable = false)
    private boolean isActivityPublic = false;

    @Builder
    public User(String email, String password, String name, String school, String department, List<String> techStacks) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.school = school;
        this.department = department;
        if (techStacks != null) {
            this.techStacks = techStacks;
        }
    }

    public void update(String name, String school, String department, List<String> techStacks,
                       String bio, String githubUrl, String blogUrl, String portfolioUrl) {
        this.name = name;
        this.school = school;
        this.department = department;
        this.techStacks = techStacks != null ? techStacks : new ArrayList<>();
        this.bio = bio;
        this.githubUrl = githubUrl;
        this.blogUrl = blogUrl;
        this.portfolioUrl = portfolioUrl;
    }

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updatePrivacy(boolean isProjectPublic, boolean isProfilePublic, boolean isActivityPublic) {
        this.isProjectPublic = isProjectPublic;
        this.isProfilePublic = isProfilePublic;
        this.isActivityPublic = isActivityPublic;
    }
}
