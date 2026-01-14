package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
