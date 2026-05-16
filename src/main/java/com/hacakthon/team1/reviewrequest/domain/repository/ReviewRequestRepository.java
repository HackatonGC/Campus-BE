package com.hacakthon.team1.reviewrequest.domain.repository;

import com.hacakthon.team1.reviewrequest.domain.entity.ReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRequestRepository extends JpaRepository<ReviewRequest, Long> {

    List<ReviewRequest> findAllByProjectId(Long projectId);

    List<ReviewRequest> findAllByRequesterId(Long requesterId);
}
