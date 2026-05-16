package com.hacakthon.team1.user.domain.repository;

import com.hacakthon.team1.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
