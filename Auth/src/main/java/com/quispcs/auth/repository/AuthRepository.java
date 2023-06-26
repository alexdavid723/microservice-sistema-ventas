package com.quispcs.auth.repository;

import com.quispcs.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUserName(String userName);
}
