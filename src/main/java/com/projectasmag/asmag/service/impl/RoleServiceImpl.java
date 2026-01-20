package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.dto.role.RoleResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.NotFoundException;
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
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDTO> responseDTOs = roles.stream()
                .map(this::mapToRoleResponseDTO)
                .toList();
        return responseDTOs;
    }

    @Override
    public RoleResponseDTO getRole(String id) {
        UUID roleId = UUID.fromString(id);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role Is Not Found"));
        return mapToRoleResponseDTO(role);
    }

    private RoleResponseDTO mapToRoleResponseDTO(Role role) {
        RoleResponseDTO response = new RoleResponseDTO(role.getId(), role.getName());
        return response;
    }
}
