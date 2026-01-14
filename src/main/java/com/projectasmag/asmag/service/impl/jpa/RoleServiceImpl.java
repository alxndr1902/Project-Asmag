package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.dto.role.RoleResponseDTO;
import com.projectasmag.asmag.model.company.Role;
import com.projectasmag.asmag.repository.RoleRepository;
import com.projectasmag.asmag.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        return roleRepository.findAll().stream()
                .map(v -> new RoleResponseDTO(v.getId(), v.getName()))
                .toList();
    }

    @Override
    public RoleResponseDTO getRole(String id) {
        Role role = roleRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Role Found"));
        return new RoleResponseDTO(role.getId(), role.getName());
    }
}
