package com.example.dgsdemo.repository;

import com.example.dgsdemo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    // Custom queries can be added here if needed
}