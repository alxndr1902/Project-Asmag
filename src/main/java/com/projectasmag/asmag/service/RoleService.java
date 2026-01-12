package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.user.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getRoles();

    RoleResponseDTO getRole(String id);
}
