package com.example.springbootcustomermanagement.repository;

import com.example.springbootcustomermanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
