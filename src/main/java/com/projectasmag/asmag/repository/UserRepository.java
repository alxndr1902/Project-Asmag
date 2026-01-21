package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByRoleCode(String code);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
