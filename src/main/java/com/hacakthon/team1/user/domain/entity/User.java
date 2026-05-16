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

    public void update(String name, String school, String department, List<String> techStacks) {
        this.name = name;
        this.school = school;
        this.department = department;
        this.techStacks = techStacks != null ? techStacks : new ArrayList<>();
    }
}
