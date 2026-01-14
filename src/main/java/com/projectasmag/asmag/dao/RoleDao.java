package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.company.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleDao {
    Role insert(Role role);

    Role update(Role role);

    List<Role> findAll();

    Optional<Role> findById(UUID id);

    void deleteById(UUID id);
}
