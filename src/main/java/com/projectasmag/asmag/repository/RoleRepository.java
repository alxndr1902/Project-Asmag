package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
