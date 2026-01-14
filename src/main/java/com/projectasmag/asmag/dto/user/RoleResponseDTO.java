package com.projectasmag.asmag.dto.user;

import java.util.UUID;

public class RoleResponseDTO {
    private UUID id;
    private String name;

    public RoleResponseDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
