package com.hacakthon.team1.reviewrequest.domain.entity;

import com.hacakthon.team1.domain.entity.BaseEntity;
import com.hacakthon.team1.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_requests")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User requester;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(length = 255)
    private String githubUrl;

    @Builder
    public ReviewRequest(User requester, Long projectId, String title, String content, String githubUrl) {
        this.requester = requester;
        this.projectId = projectId;
        this.title = title;
        this.content = content;
        this.githubUrl = githubUrl;
    }
}
