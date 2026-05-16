package com.hacakthon.team1.teamapplication.domain.entity;

import com.hacakthon.team1.domain.entity.BaseEntity;
import com.hacakthon.team1.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_applications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamApplication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long projectId;

    @Column
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Builder
    public TeamApplication(User user, Long projectId, String message) {
        this.user = user;
        this.projectId = projectId;
        this.message = message;
        this.status = ApplicationStatus.PENDING;
    }

    public void updateStatus(ApplicationStatus status) {
        this.status = status;
    }
}
