package com.example.springbootcustomermanagement.repository;

import com.example.springbootcustomermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
