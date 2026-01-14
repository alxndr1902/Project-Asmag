package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.user.RoleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleResponseDTO> getRoles();

    RoleResponseDTO getRole(String id);
}
