package com.example.dgsdemo.repository;

import com.example.dgsdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom queries can be added here if needed
}