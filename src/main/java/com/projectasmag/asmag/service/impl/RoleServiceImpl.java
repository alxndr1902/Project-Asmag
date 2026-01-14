package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.dao.RoleDao;
import com.projectasmag.asmag.dto.user.RoleResponseDTO;
import com.projectasmag.asmag.model.company.Role;
import com.projectasmag.asmag.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        return roleDao.findAll().stream()
                .map(v -> new RoleResponseDTO(v.getId(), v.getName()))
                .toList();
    }

    @Override
    public RoleResponseDTO getRole(String id) {
        Role role = roleDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Role Found")
        );
        return new RoleResponseDTO(role.getId(), role.getName());
    }
}
