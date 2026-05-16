package com.hacakthon.team1.user.domain.entity;

import com.hacakthon.team1.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private int grade;

    @Builder
    public User(String email, String password, String name, String studentId, String department, int grade) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.grade = grade;
    }
}
