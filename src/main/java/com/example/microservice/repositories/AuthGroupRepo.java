package com.example.microservice.repositories;

import com.example.microservice.entity.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthGroupRepo extends JpaRepository<AuthGroup, Long> {

    List<AuthGroup> findByUserId(Long userId);
}
